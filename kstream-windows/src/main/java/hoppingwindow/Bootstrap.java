package hoppingwindow;

import configs.AppConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serdes.AppSerdes;
import types.SimpleInvoice;

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


        //Hopping Window Example


        KTable<Windowed<String>,Long>KT0=KS0.
                groupByKey(Grouped.with(AppSerdes.String(),AppSerdes.SimpleInvoice())).
                             windowedBy(TimeWindows.of(Duration.ofMinutes(5)) //Window Duration
                                     .advanceBy(Duration.ofMinutes(1))). //Amount of minutes that will slide
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