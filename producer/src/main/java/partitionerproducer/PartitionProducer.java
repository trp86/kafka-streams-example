package partitionerproducer;

import configs.AppConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class PartitionProducer {

    public static void main (String [] args) throws Exception
    {
        System.out.println("Creating Kafka Producer....");
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.applicationID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, OddEvenPartitioner.class.getName());

        KafkaProducer<Integer,String> producer=new KafkaProducer<Integer, String>(producerProperties);
        RecordMetadata metadata;

        for (int i=0;i<=AppConfigs.numEvents;i++)
        {
            metadata=producer.send(new ProducerRecord<>(AppConfigs.topicName,i,"SimpleMessage:-"+i)).get();
            System.out.println("Message " + i + " persisted with offset " + metadata.offset()
                    + " in partition " + metadata.partition());
        }

        producer.close();
        System.out.println("Message producing completed...");

    }

}
