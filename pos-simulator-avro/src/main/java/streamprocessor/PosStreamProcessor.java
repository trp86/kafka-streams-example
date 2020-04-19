package streamprocessor;

import configs.AppConfigs;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serdes.AppSerdes;
import types.PosInvoice;

import java.util.Properties;

public class PosStreamProcessor {

    private static final Logger logger = LogManager.getLogger(PosStreamProcessor.class);

    public static void main(String[] args) {
        Properties streamProps = new Properties();
        streamProps.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationID);
        streamProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);

        //Streams Builder
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        //Create KStream object
        KStream<String, PosInvoice> KS0= streamsBuilder.stream(
                AppConfigs.topicName, //Kafka Topic Name
                Consumed.with(AppSerdes.String(),AppSerdes.PosInvoice()) //Key and value serdes for the input kafka topic
        );

        //Filter records and produce data to a kafka topic
        KS0.filter(
                (k,v) -> v.getDeliveryType().toString().equalsIgnoreCase(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY)  //Filter records having delivery type as HOME-DELIVERY
        ).
                to(AppConfigs.shipment_topicName, Produced.with(AppSerdes.String(),AppSerdes.PosInvoice())); //Send the records to a topic with corrospending serilaizer and deserializer




        KS0.filter(
                (k,v) -> v.getCustomerType().toString().equalsIgnoreCase(AppConfigs.CUSTOMER_TYPE_PRIME)).
                mapValues(invoice -> RecordBuilder.getNotification(invoice)).
                to(AppConfigs.loyality_notification_topicname, Produced.with(AppSerdes.String(),AppSerdes.Notification()));

        KS0.mapValues(
                invoice -> RecordBuilder.getMaskedInvoice(invoice)
        ).flatMapValues(invoice -> RecordBuilder.getHadoopRecords(invoice)).to(AppConfigs.hadoop_topicName, Produced.with(AppSerdes.String(),AppSerdes.HadoopRecord()));


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
