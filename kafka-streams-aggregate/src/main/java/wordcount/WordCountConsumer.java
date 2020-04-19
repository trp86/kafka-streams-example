package wordcount;

import configs.AppConfigs;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Properties;

public class WordCountConsumer {

    private static final Logger logger = LogManager.getLogger(WordCountConsumer.class);

    public static void main(String[] args) {
        Properties streamProps = new Properties();
        streamProps.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        streamProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        streamProps.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        streamProps.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        streamProps.put(StreamsConfig.STATE_DIR_CONFIG,AppConfigs.stateStoreLocation);

        //Streams Builder
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        //Create KStream object
        KStream<String, String> KS0= streamsBuilder.stream(
                AppConfigs.topicName, //Kafka Topic Name
                Consumed.with(Serdes.String(),Serdes.String()) //Key and value serdes for the input kafka topic
        );



        KS0.print(Printed.<String,String>toSysOut().withLabel("KS0")); //Prints on the console

        //METHOD 1 Using groubykey method
        KStream<String, Integer> KS1=KS0.flatMapValues(v -> Arrays.asList(v.split(" "))).map((k,v) -> new KeyValue<String,Integer>(v,1));
       // KS1.print(Printed.<String,Integer>toSysOut().withLabel("KS1")); //Prints on the console
        //Group by on key
        //Default key and value configs here are defined as string.But in this transformation the Value serializer is changed to
        // integer instead of string.hence we have ovverride the default value serliazer.Below are some links for reference
        //https://stackoverflow.com/questions/53283232/kafkastreams-how-to-specify-serdes-in-stream-aggregation
        //https://docs.confluent.io/current/streams/javadocs/org/apache/kafka/streams/kstream/Grouped.html
        KGroupedStream<String,Integer> KS2= KS1.groupByKey(Grouped.with(Serdes.String(),Serdes.Integer()));


        KTable<String,Long> KS3=KS2.count();
        KS3.toStream().print(Printed.<String,Long>toSysOut().withLabel("KS3")); //Prints on the console


        //METHOD 2 Using groupby method
       /* KStream<String, String> KS1=KS0.flatMapValues(v -> Arrays.asList(v.split(" ")));
        KGroupedStream KS2 =KS1.groupBy((k,v)->v);
        KTable<String, Long> count=KS2.count();
        count.toStream().print(Printed.<String,Long>toSysOut().withLabel("COUNT")); //Prints on the console */

        //Create Topology
        Topology topology = streamsBuilder.build();

        //Create kafka streams object
        KafkaStreams streams = new KafkaStreams(topology,streamProps);
        logger.info("Starting Streams....");
        //Start the streams
        streams.start();

        //Gracefully shutdown the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    logger.info("Shutting down stream");
                    streams.close();
                }
        ));
    }

}
