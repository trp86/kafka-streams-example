package configs;

public class AppConfigs {
    public final static String applicationID = "streaming-k-table";
    public final static String bootstrapServers = "localhost:9092,localhost:9093";
    public static final String topicName="stock-tick";


    public static final String stateStoreLocation="scripts/tmp/state-store";
    public static final String stateStoreName="k01-store-test";
    public static final String regExsymbol="(?i)HDFCBANK|TCS";
    public static final String queryServerHost="localhost";
    public static final int queryServerport=7010;
}
