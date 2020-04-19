package config;

public class AppConfigs {

    public final static String applicationID = "KStreamJoinDemo";
    public final static String applicationID_kstream2globalktable = "KStreamToGlobalKtableJoin";
    public final static String bootstrapServers = "localhost:9092,localhost:9093";
    public final static String paymentRequestTopicName = "payment_request";
    public final static String inventoryTopicName="active-inventories";
    public final static String clicksTopicName="ad-clicks";
    public final static String paymentConfirmationTopicName = "payment_confirmation";
    public final static String stateStoreName = "tmp/state-store";
    public final static String top3AggregateKey = "top3NewsTypes";
}