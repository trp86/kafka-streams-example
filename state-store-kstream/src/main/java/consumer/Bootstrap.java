package consumer;

import configs.AppConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import partitioner.RewardPartitioner;
import types.PosInvoice;
import serdes.*;
import java.util.Properties;


public class Bootstrap {

    private static final Logger logger = LogManager.getLogger(Bootstrap.class); ;

    public static void main(String[] args) {

        Properties streamProps = new Properties();
        streamProps.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        streamProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);

        //Streams Builder
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        //Create KStream object
        //Filter prime customers
        KStream<String, PosInvoice> KS0= streamsBuilder.stream(
                AppConfigs.topicName, //Kafka Topic Name
                Consumed.with(AppSerdes.String(),AppSerdes.PosInvoice()) //Key and value serdes for the input kafka topic
        ).filter((k,v) -> v.getCustomerType().trim().equalsIgnoreCase(AppConfigs.CUSTOMER_TYPE_PRIME));


        KS0.through(AppConfigs.t_rewards_notification_topicname,
                Produced.with(AppSerdes.String(),AppSerdes.PosInvoice(),new RewardPartitioner()));

        //Store Builder
        StoreBuilder kvStoreBuilder = Stores.keyValueStoreBuilder(
                Stores.inMemoryKeyValueStore(AppConfigs.REWARDS_STORE_NAME),
                AppSerdes.String(),
                AppSerdes.Double()
        );

        //Add the storebuilder to streams builder
        streamsBuilder.addStateStore(kvStoreBuilder);

        //State Store can be implemented in 2 ways:-
        //  1)In Memory  2)Persistent
        //For persistent state store kafka uses roxdb to store the information and for the in memory it stores in memory.
        //In order to acheive fault tolerant kafka uses state store logs and this backed up periodically.Hence if any broker goes down kafka
        //takes care of re-balancing.
        //NOTE State store in kafka streams is always at a partition level.It is not global for a Kafka topic.
        //We need to make sure the data is properly partitioned and specific key data always go to the same partition.
        //Hence we are using through transformation to change the partitioning of the data.

        KS0.through(AppConfigs.t_rewards_notification_topicname, //Temporary topic name
                Produced.with(AppSerdes.String(),AppSerdes.PosInvoice(), //Key and Value pair serdes
                        new RewardPartitioner())) //custom partioner class
                .transformValues(() -> new Rewardstransformer(),AppConfigs.REWARDS_STORE_NAME)
           .to(AppConfigs.rewards_notification_topicname,
                   Produced.with(AppSerdes.String(),AppSerdes.Notification()));

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
