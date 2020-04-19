package consumer;

import configs.AppConfigs;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import serdes.AppSerdes;
import types.Notification;
import types.PosInvoice;
import java.util.Properties;

public class BootstrapWithAggregateFunctions {
    private static final Logger logger = LogManager.getLogger(BootstrapWithAggregateFunctions.class); ;
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

        //Change the key and value pair from <String,PosInvoice> to <String,Notification> (i.e. <CustomerCardNumber,Notification>)
        KStream<String, Notification> KS1 = KS0.map((key,invoice) ->new KeyValue<String,Notification>(invoice.getCustomerCardNo(),Notification.getNotificationFromInvoice(invoice)));

        //Group the data by Customer card number
        KGroupedStream<String,Notification> KGS0=KS1.groupByKey(Grouped.with(AppSerdes.String(),AppSerdes.Notification()));

        //Calculate the new loyality points
        KTable<String,Notification> KS2=KGS0.reduce((aggValue, newValue) ->{
            newValue.setTotalLoyalityPoints(newValue.getEarnedLoyalityPoints()+aggValue.getEarnedLoyalityPoints());
            return newValue;});

        //Push data to new kafka topic
        KS2.toStream().to(AppConfigs.rewards_notification_topicname,Produced.with(AppSerdes.String(),AppSerdes.Notification()));

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