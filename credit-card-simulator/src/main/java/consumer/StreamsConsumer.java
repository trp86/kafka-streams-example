package consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import config.AppConfigs;
import types.CreditCardTxn;
import serdes.AppSerdes;
import java.util.Properties;


public class StreamsConsumer {



    public static void main(String[] args) {

        RecordBuilder recordBuilder = new RecordBuilder();

        Properties streamProps = new Properties();
        streamProps.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        streamProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
/*        streamProps.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        streamProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
       streamProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");*/

        //Streams Builder
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        //Create KStream object
        KStream<String, CreditCardTxn> ccTxnStream= streamsBuilder.stream(
                AppConfigs.topicName, //Kafka Topic Name
                Consumed.with(AppSerdes.String(),AppSerdes.CreditCardTxn()) //Key and value serdes for the input kafka topic
        );

        //Data Enrich
        KStream<String, CreditCardTxn> ccTxnStreamDataEnrich=ccTxnStream.map((k,v) -> new KeyValue<>(k,recordBuilder.enrich(v)));

        //Filter and send to a kafka topic for logged in users
        ccTxnStreamDataEnrich.filter((k,v) -> v.getIsLoggedIn())
                             .map((k,v) -> new KeyValue<>(k,recordBuilder.generateLoggedInUserObject(v)))
                             .to(AppConfigs.loggedInUserstopicName, Produced.with(AppSerdes.String(), AppSerdes.LoggedIn()));

        //Filter and send to a kafka topic for not logged in users
        ccTxnStreamDataEnrich.filter((k,v) -> !v.getIsLoggedIn())
                .map((k,v) -> new KeyValue<>(k,recordBuilder.generateNotLoggedInUserObject(v)))
                .to(AppConfigs.notloggedInUserstopicName, Produced.with(AppSerdes.String(), AppSerdes.NotloggedIn()));

        //Create Topology
        Topology topology = streamsBuilder.build();

        //Create kafka streams object
        KafkaStreams streams = new KafkaStreams(topology,streamProps);

        //Start the streams
        streams.start();

        //Gracefully shutdown the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    streams.close();
                }
        ));
    }


}
