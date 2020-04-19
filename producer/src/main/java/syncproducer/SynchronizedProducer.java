package syncproducer;

import configs.AppConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Timestamp;
import java.util.Properties;

public class SynchronizedProducer {

    private static final Logger logger = LogManager.getLogger();

    public static void main (String [] args)
    {
        System.out.println("Creating Kafka Producer....");
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //We want to raise an exception - So, do not retry.
        producerProperties.put(ProducerConfig.RETRIES_CONFIG,0);
        //We want to raise an exception - So, take acknowledgement only when message is persisted to all brokers in ISR
        producerProperties.put(ProducerConfig.ACKS_CONFIG,"all");

        KafkaProducer<Integer,String> producer=new KafkaProducer<Integer, String>(producerProperties);
        RecordMetadata metadata;


         /*
            Follow below steps to generate exception.
            1. Start three node cluster
            2. Create topic with --config min.insync.replicas=3
            3. Start producer application
            4. Shutdown one broker while producer is running - It will cause NotEnoughReplicasException
        */

        try
        {
            for (int i = 1; i <= AppConfigs.numEvents; i++) {
                System.out.println("ENTER");
                Thread.sleep(1000);
                metadata = producer.send(new ProducerRecord<>(AppConfigs.topicName_synchronizedProducer, i, "Simple Message-" + i)).get();
                System.out.println("Message " + i + " persisted with offset " + metadata.offset()
                        + " and timestamp on " + new Timestamp(metadata.timestamp()));
            }
        }

        catch(Exception e)
        {
            System.out.println("Can't send message - Received exception \n" + e.getMessage());
        }

        finally
        {
            producer.close();
        }
        logger.info("Message producing completed...");







    }

}


