package configs;

public class AppConfigs {
    public final static String applicationID = "wordcount";
    public final static String bootstrapServers = "localhost:9092,localhost:9093";
    public static final String topicName="wc";
    public static final String stateStoreLocation="scripts/tmp/state-store";

    //EMPLOYEE SALARY AGGREGATE
    public final static String emp_agg_applicationID = "KStreamAggDemo";
    public final static String emp_agg_topicName = "employees";
    public final static String stateStoreName = "state-store";

    //PERSON AGE AGGREGATE
    public final static String person_age_applicationID = "AgeCountDemo";
    public static final String person_age_topicName="person-age";
}
