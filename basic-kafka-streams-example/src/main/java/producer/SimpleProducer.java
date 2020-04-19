package producer;

import configs.AppConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;


public class SimpleProducer {

    private static final Logger logger = LogManager.getLogger(SimpleProducer.class);

    public static void main (String [] args)
    {
        System.out.println("Creating Kafka Producer....");
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfig.applicationID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfig.bootstrapServers);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer,String> producer=new KafkaProducer<Integer, String>(producerProperties);

        for (int i=0;i<=AppConfig.numEvents;i++)
        {
            producer.send(new ProducerRecord<>(AppConfig.topicName,i,"SimpleMessage:-"+i));
        }

        producer.close();
        System.out.println("Message producing completed...");

    }

}
