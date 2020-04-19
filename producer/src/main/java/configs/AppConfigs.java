package configs;

public class AppConfigs {
    public final static String applicationID = "StorageDemo";
    public final static String bootstrapServers = "localhost:9092,localhost:9093";
    public final static String topicName = "invoice";
    public final static int numEvents = 500000;

    public final static String kafkaProperties="kafka.properties";

    //Multi Thread Producer
    public final static String applicationID_multiThreadProducer = "Multi-Thread-Producer";
    public final static String topicName_multiThreadProducer = "nse-eod";
    public final static String[] eventFiles_multiThreadProducer = {"data/NSE05NOV2018BHAV.csv","data/NSE06NOV2018BHAV.csv"};

    //Transactional Producer
    public final static String applicationID_transactionalProducer = "TransactionalProducer";
    public final static String topicName1 = "txntopic1";
    public final static String topicName2 = "txntopic2";
    public final static int numEvents_transactionalProducer = 2;
    public final static String transactionID = "txn-id";

    //Synchronized Producer
    public final static String topicName_synchronizedProducer = "sync-producer-test3";


}
