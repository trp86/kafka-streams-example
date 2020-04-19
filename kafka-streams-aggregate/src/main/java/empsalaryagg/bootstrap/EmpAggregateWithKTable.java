package empsalaryagg.bootstrap;

import configs.AppConfigs;
import empsalaryagg.types.DepartmentAggregate;
import empsalaryagg.types.Employee;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.kstream.internals.KTableAggregate;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;
import empsalaryagg.serdes.AppSerdes;
import empsalaryagg.*;

public class EmpAggregateWithKTable {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.emp_agg_applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreLocation);

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KTable<String, Employee> KT0 = streamsBuilder.table(AppConfigs.emp_agg_topicName,Consumed.with(AppSerdes.String(), AppSerdes.Employee()));

        //Group By
        //NOTE There is a difference between groupby statements for KTable and KStream.KTable takes a KeyValue Pair and returns abck a KeyValue Pair
        //But in case of KStream it takes a KeyValue pair and returns back only a value. Below is the code.
        // KGroupedStream<String,Employee> KGS1= KS0.groupBy((k,v) -> v.getDepartment(),Grouped.with(AppSerdes.String(),AppSerdes.Employee()));
        KGroupedTable<String,Employee> KGT0=KT0.groupBy((k,v) -> KeyValue.pair(v.getDepartment(),v),Grouped.with(AppSerdes.String(),AppSerdes.Employee()));

        //Aggregate
        KGT0.aggregate(
                //Initalizer .Initializes the state store when first time it is called
                () -> new DepartmentAggregate().
                        withAvgSalary(0D).
                        withEmployeeCount(0).
                        withTotalSalary(0),
                //Adder Adds to the aggregate value when the key is a new one.
                (k,v,newValue) -> new DepartmentAggregate().
                        withAvgSalary(v.getSalary()+newValue.getTotalSalary()/1D+newValue.getEmployeeCount()).
                        withEmployeeCount(1+newValue.getEmployeeCount()).
                        withTotalSalary(v.getSalary()+newValue.getTotalSalary()),
                //Subtractor Substracts to the aggregate value when the key is already present in KTable.
                (k,v,newValue) -> new DepartmentAggregate().
                        withAvgSalary(newValue.getTotalSalary()-v.getSalary()/newValue.getEmployeeCount()-1D).
                        withEmployeeCount(newValue.getEmployeeCount()-1).
                        withTotalSalary(newValue.getTotalSalary()-v.getSalary()),
                //Serializer
                Materialized.
                        <String,DepartmentAggregate, KeyValueStore<Bytes,byte []>>as(AppConfigs.stateStoreName).
                        withKeySerde(AppSerdes.String()).
                        withValueSerde(AppSerdes.DepartmentAggregate())
        ).toStream().print(Printed.<String,DepartmentAggregate>toSysOut().withLabel("Department Aggregate"));;

        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Streams");
            streams.close();
        }));

    }

}
