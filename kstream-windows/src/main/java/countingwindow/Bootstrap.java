package countingwindow;

import serdes.AppSerdes;
import types.SimpleInvoice;
import configs.AppConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Properties;


public class Bootstrap {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfig.stateStoreName);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, SimpleInvoice> KS0 = streamsBuilder.stream(AppConfig.posTopicName,
                Consumed.with(AppSerdes.String(), AppSerdes.SimpleInvoice())
                        .withTimestampExtractor(new InvoiceTimeExtractor()) //
        );


        //Tumbling Window:-If we need to perform aggregtaion within a time window then Kafka Stream gives us a option to do that.First we have to
        // do a group by and then give the time window.Below line of code gives a window of 5 minutes.This time window is called as a tumbling
        // window.Below code performs a count of invoces coming within 5 minutes of window.Kafka Stream assigns a unique windowid for every window
        // For example the first window with window id 2051079849 starts from 2019-02-05T10:00:10.00Z to 2019-02-05T10:05:10.00Z then all records
        // with same key will fall into this window.Considering data like below. <Key=StoreID and Value=Invoice>
         /*STR1534:{"InvoiceNumber": 101,"CreatedTime": "2019-02-05T10:00:10.00Z","StoreID": "STR1534","TotalAmount": 1920}
        STR1535:{"InvoiceNumber": 102,"CreatedTime": "2019-02-05T10:00:40.00Z","StoreID": "STR1535","TotalAmount": 1860}
        STR1534:{"InvoiceNumber": 103,"CreatedTime": "2019-02-05T10:01:11.00Z","StoreID": "STR1534","TotalAmount": 2400}
        STR1535:{"InvoiceNumber": 104,"CreatedTime": "2019-02-05T10:02:11.00Z","StoreID": "STR1535","TotalAmount": 8936}
        STR1535:{"InvoiceNumber": 105,"CreatedTime": "2019-02-05T10:03:15.00Z","StoreID": "STR1535","TotalAmount": 6375}
        STR1534:{"InvoiceNumber": 106,"CreatedTime": "2019-02-05T10:04:12.00Z","StoreID": "STR1534","TotalAmount": 9365}
        STR1534:{"InvoiceNumber": 107,"CreatedTime": "2019-02-05T10:07:11.00Z","StoreID": "STR1534","TotalAmount": 5276}*/
        //Then for window id 2051079849 that starts from 2019-02-05T10:00:10.00Z to 2019-02-05T10:05:10.00Z we will have record counts like below:-
        // Window id (2051079849) Store ID (STR1534) Count (3)
        // Window id (2051079849) Store ID (STR1535) Count (3)
        //Then for next window id 2051079850 that starts from 2019-02-05T10:05:10.00Z to 2019-02-05T10:10:10.00Z we will have record counts like below:-
        // Window id (2051079850) Store ID (STR1534) Count (1)

        //Now in  a case if another record arrives late .
        //STR1534:{"InvoiceNumber": 111,"CreatedTime": "2019-02-05T10:02:10.00Z","StoreID": "STR1534","TotalAmount": 620}
        //Then KStream will check the CreatedTime and identify the windowid.In this case the window id will be 2051079849 and the aggregated value
        // i.e. (count) will be updated.Now the aggregated value will look like below.
        // Window id (2051079849) Store ID (STR1534) Count (4)
        // Window id (2051079849) Store ID (STR1535) Count (3)
        //This happens as the return type is a KTable and KTable allows the updates also.

        // Q) Which time does KStream consider for time aggregation?
        // A) In this usecase we have considered invoice created time.Check InvoiceTimeExtractor.java.

        //Grace Period:-The amount of time till which KStream will wait for the record to consider it for aggregation.
        //In the below code grace period is fr 2 minutes.

        //For example:- First record arrives with invoice create time as 10:00:00.00Z.So the baseline time will be  at 10:00.00Z
        // and window start time = 10:00.00Z & Window end time= 10:05.00Z.If a record with invoice create time as 10:02:00.00Z
        // then it is considered as first window and if a record with invoice create time as 10:07:00.00Z is considered as second window.
        // So now kafka baseline time will be the maximum of invoice create time.(i.e. 10:07:00.00Z).
        //Record time frame that will be considerd for aggregation = Baseline Time - Grace Period TIme
        //Now baseline time =  maximum of invoice create time = 10:07:00.00Z. Grace Time = 2 minutes
        //Only records coming with a invoice create time >= 10:05:00.00Z will be considered for aggregation.
        //Default grace period is 24 hours


        KTable<Windowed<String>,Long>KT0=KS0.
                groupByKey(Grouped.with(AppSerdes.String(),AppSerdes.SimpleInvoice())).
                             windowedBy(TimeWindows.of(Duration.ofMinutes(5)) //Window Duration
                                        .grace(Duration.ofMinutes(2)) //Grace Period:-Records arrinving after this duration will not be considered for aggragtion
                                     ).
                count();

        KT0.toStream().foreach(
                (windowedKey,value) ->System.out.println(
                                "Store ID:"+windowedKey.key()+
                                        "Window ID:"+windowedKey.window().hashCode()+
                                        "Window Start:"+ Instant.ofEpochMilli(windowedKey.window().start()).atOffset(ZoneOffset.UTC)+
                                        "Window End:"+ Instant.ofEpochMilli(windowedKey.window().end()).atOffset(ZoneOffset.UTC)+
                                        "Count:"+value
                                )

        );


        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Streams");
            streams.close();
        }));

    }
}