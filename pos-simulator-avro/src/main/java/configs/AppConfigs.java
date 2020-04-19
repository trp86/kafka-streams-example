package configs;

public class AppConfigs {
    public final static String applicationID = "pos-simulator-avro";
    public final static String bootstrapServers = "localhost:9092,localhost:9093";
    public final static String schemaRegistryURL = "http://localhost:8081";

    public static final String topicName="pos";


    //Streams Processing
    public final static String posfanout_applicationID = "pos-fanout";
    public static final String shipment_topicName="shipment";
    public static final String loyality_notification_topicname="loyality";
    public static final String hadoop_topicName="hadoop-sink";
    public static final String DELIVERY_TYPE_HOME_DELIVERY="HOME-DELIVERY";
    public static final String CUSTOMER_TYPE_PRIME="PRIME";
    public static final Double LOYALITY_FACTOR=0.02;



}
