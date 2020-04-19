package simpleproducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import configs.AppConfigs;
import java.util.Properties;


public class SimpleProducer {

    private static final Logger logger = LogManager.getLogger();

    public static void main (String [] args)
    {
        System.out.println("Creating Kafka Producer....");
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.applicationID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer,String> producer=new KafkaProducer<Integer, String>(producerProperties);

        for (int i=0;i<=AppConfigs.numEvents;i++)
        {
            producer.send(new ProducerRecord<>(AppConfigs.topicName,i,"SimpleMessage:-"+i));
        }

        producer.close();
        System.out.println("Message producing completed...");

    }

}
