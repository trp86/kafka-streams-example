package bootstrap;

import config.AppConfigs;
import serdes.AppSerdes;
import types.PaymentConfirmation;
import types.PaymentRequest;
import types.TransactionStatus;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.util.Properties;


public class KStreamToKStreamJoin {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreName);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, PaymentRequest> KS0 = streamsBuilder.stream(AppConfigs.paymentRequestTopicName,
                Consumed.with(AppSerdes.String(), AppSerdes.PaymentRequest())
                        .withTimestampExtractor(AppTimeExtractor.PaymentRequest())
        );

        KStream<String, PaymentConfirmation> KS1 = streamsBuilder.stream(AppConfigs.paymentConfirmationTopicName,
                Consumed.with(AppSerdes.String(), AppSerdes.PaymentConfirmation())
                        .withTimestampExtractor(AppTimeExtractor.PaymentConfirmation())
        );

        //There are 4 types of join
        //1)KStream-KStream :- Output (KStream) | Join Type (Inner,Left,Outer,Swapping for Right(Alternative to Right join))|Windowed,Key-Based
        //2)KTable-KTable :- Output (KTable) | Join Type (Inner,Left,Outer,Swapping for Right(Alternative to Right join))|Non-Window,Key-Based
        //3)KStream-KTable :- Output (KStream) | Join Type (Inner,Left,Outer)|Non-Window,Key-Based
        //4)KStream-GlobalKTable :- Output (KStream) | Join Type (Inner,Left)|Non-Window,Key-Based or Non key based

        //Joins are performed on the valid key.Only when there is a join between KStream and Global KTable then a join without a key is possible.
        //Kafka Topics from where the KStream and KTable is formed must have same number of partitions and the same partition statergy
        // (i.e. They should have the same partitioner).After the join operation is completed the target kafka topic to which data is written
        // also should have the same partition statergy.This helps to have the same key data to the same partition.
        //But there is only one exception (i.e. KStream-GlobalKTable join).In this case the join operation can be done irrespective of the partition
        //statergy followed by the KStream.GlobalKTable is present on every instance.So when the join operation is performed then for every stream
        //task there will be a full local copy of the GlobalKtable present.Hence we dont neeed a partition startery for GlobalKTable.



        KS0 //Left table
                .join(KS1 //Right Table
                        ,
                        (v1/*Left table record*/, v2/*Right table record*/) ->
                        new TransactionStatus() /*Output after join.Join operation is done on the key.In this case it is transactionid.only truggered if join condition satisfies*/
                                .withTransactionID(v1.getTransactionID())
                                .withStatus((v1.getOTP().equals(v2.getOTP()) ? "Success" : "Failure")),
                JoinWindows.of(Duration.ofMinutes(5)), //Till how much time it will consider the matching record.for example in this scenario if the left side table has a created time of 10:00:00AM,then the records of the right side table with created time <= 10:05:00 will be considered or else it will be ommited.
                Joined.with(AppSerdes.String() /*Kafka topic Key Serde.Both left and right side table have the same key serde.*/,
                        AppSerdes.PaymentRequest() /*Left Side Table Value Serde*/,
                        AppSerdes.PaymentConfirmation() /*Right Side Table Value Serde*/
                )
        ).print(Printed.toSysOut());

        logger.info("Starting Stream...");
        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Streams...");
            streams.close();
        }));

    }
}