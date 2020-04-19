package streamprocessor;

import configs.AppConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;

public class ProcessStreams {

    private static final Logger logger = LogManager.getLogger(ProcessStreams.class);

    public static void main (String [] args) {

        //Create Streams properties object
        Properties streamProperties = new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.applicationID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.bootstrapServers);
        streamProperties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        streamProperties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        //Create streams builder
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        //Subscribe to a topic
        KStream<Integer,String> kstream=streamsBuilder.stream(AppConfig.topicName);
        //Perform transformations
        kstream.foreach((k,v) -> System.out.println("KEY:::---"+k+"VALUE:::----"+v));

        //Create Topology
        Topology topology = streamsBuilder.build();

        //Create kafka streams object
        KafkaStreams streams = new KafkaStreams(topology,streamProperties);
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
