package ktable;

import configs.AppConfigs;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;


public class StreamingTableApp {

    //KTABLE
    /*
        1)KTABLE is an update stream backed by a local state store
        2)Records are UPSERTED to KTABLE
        3)Record with a key and null value is a delete operation for that key in ktable
        4)KTable can be used in the same way of KStream
        5)KTABLE is local in nature.internally it uses state store.
    */
    private static final Logger logger = LogManager.getLogger(StreamingTableApp.class);
    public static void main(String[] args) {

        Properties streamProps = new Properties();
        streamProps.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        streamProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        streamProps.put(StreamsConfig.STATE_DIR_CONFIG,AppConfigs.stateStoreLocation);
        streamProps.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        streamProps.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        //Streams Builder
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        //Create KTable object by consuming data from Kafka topic
        KTable<String,String> KT0 = streamsBuilder.table(AppConfigs.topicName/*,Materialized.as(AppConfigs.stateStoreName)*/); //As we havenot prvided a state store name hence kafka internally would have named it.


        KT0.toStream().  //Converts KTable to KStream
                print(Printed.<String,String>toSysOut().withLabel("KT0")); //Prints on the console

        //Filters KTable and converts a new KTable
        KTable<String,String> KT1=KT0.filter(
                (k,v) -> k.matches(AppConfigs.regExsymbol) && !v.isEmpty(), //Filter condition
                 Materialized.as(AppConfigs.stateStoreName) //Assign a name to the state store.If we dont give it kafka assigns some random name
        );

        KT1.toStream().  //Converts KTable to KStream
                print(Printed.<String,String>toSysOut().withLabel("KT1")); //Prints on the console





        //Create Topology
        Topology topology = streamsBuilder.build();


        //Create kafka streams object
        KafkaStreams streams = new KafkaStreams(topology,streamProps);


        //Query Server
        QueryServer queryServer = new QueryServer(streams,
                AppConfigs.queryServerHost,
                AppConfigs.queryServerport);
        streams.setStateListener((newState,oldState) ->
        {
            logger.info("State changing to:"+newState+" from "+oldState);
            queryServer.setActive(newState==KafkaStreams.State.RUNNING && oldState==KafkaStreams.State.REBALANCING);
        });



        logger.info("Starting Streams....");
        //Start the streams
        streams.start();

        queryServer.start();

        //Gracefully shutdown the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    logger.info("Shutting down stream");
                    queryServer.stop();
                    streams.close();
                }
        ));





    }

}
