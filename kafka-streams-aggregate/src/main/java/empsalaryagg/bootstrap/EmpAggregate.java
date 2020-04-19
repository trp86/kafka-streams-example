package empsalaryagg.bootstrap;

import configs.AppConfigs;
import empsalaryagg.types.DepartmentAggregate;
import empsalaryagg.types.Employee;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;
import empsalaryagg.serdes.AppSerdes;
import empsalaryagg.*;

public class EmpAggregate {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.emp_agg_applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreLocation);

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream <String, Employee> KS0 =streamsBuilder.stream(AppConfigs.emp_agg_topicName,
                Consumed.with(AppSerdes.String(), AppSerdes.Employee()));


        //Reduce Method return type cannot be changed.this means the key and value types of the input kstream and the output kstream should be same.
        //But in case of aggregate method the input key and value types can be changed.
        //Aggregate method has 3 steps present
        //  1)Initializer :- Initializes the state store when first time it is called .Reduce method by default calls initializer
        //  2)Aggregator :- Takes 3 parameter (k,v,newvalue) k:-Key v:-old value newvalue:-aggregated value
        //  3)Serializer :- Provides the state store name,key  serde for output and value  serde for output

        //When to use KSTream and Ktable? KStream always acts as insert streams.This means records keeps on coming as a stream and
        // I am not bothered of updates.KStream keeps on growing.But KTable always acts based on keys and do care about the upsert.
        // This means if at t0 a record came with key K1 and KTable contains the record with key as K1.If in t5 a record came with
        // same key K1 then in KTable new record will not be inserted.instead the record with key K1 will be updated. But in the
        // above scenario in KStream there will be records for key K1.

        //METHOD 1 (reduce ())
       // KGroupedStream<String,Employee>KGS0 = KS0.groupByKey();

       // KGS0.count();

       /* KGS0.reduce((v,newValue) -> new DepartmentAggregate().
                        withAvgSalary(v.getSalary()+newValue.getSalary()/1D+newValue.g).newValue
                        withEmployeeCount(1+newValue.getEmployeeCount()).
                        withTotalSalary(v.getSalary()+newValue.getTotalSalary());*/

        //METHOD 2 (aggregate())

        //Group By on department
        KGroupedStream<String,Employee> KGS1= KS0.groupBy((k,v) -> v.getDepartment(),Grouped.with(AppSerdes.String(),AppSerdes.Employee()));
        //Aggregate
        KTable<String,DepartmentAggregate> KT1=KGS1.aggregate(
                //Initializer.Initializes the state store when first time it is called
                () -> new DepartmentAggregate().
                        withAvgSalary(0D).
                        withEmployeeCount(0).
                        withTotalSalary(0),
                //Aggregator
                (k,v,newValue) -> new DepartmentAggregate().
                        withAvgSalary(v.getSalary()+newValue.getTotalSalary()/1D+newValue.getEmployeeCount()).
                        withEmployeeCount(1+newValue.getEmployeeCount()).
                        withTotalSalary(v.getSalary()+newValue.getTotalSalary()),
                //Serializer
                Materialized.
                        <String,DepartmentAggregate, KeyValueStore<Bytes,byte []>>as(AppConfigs.stateStoreName).
                        withKeySerde(AppSerdes.String()).
                        withValueSerde(AppSerdes.DepartmentAggregate())
        );

        //Convert KTable to KStream and print it
        KStream<String,DepartmentAggregate> KS1 = KT1.toStream();
        KS1.print(Printed.<String,DepartmentAggregate>toSysOut().withLabel("Department Aggregate"));

        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Streams");
            streams.close();
        }));

    }


}
