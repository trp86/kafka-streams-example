package configs;

public class AppConfig {

    public final static String applicationID = "CountingWindowApp";
    public final static String bootstrapServers = "localhost:9092,localhost:9093,localhost:9094";
    public final static String posTopicName = "simple-invoice";
    public final static String heartBeatTopicName = "heartbeat";
    public final static String stateStoreName = "tmp/state-store";


    public final static String applicationID_sessionWindow = "CountingSessionApp";

    public final static String userclick_topicName = "user-clicks";



}