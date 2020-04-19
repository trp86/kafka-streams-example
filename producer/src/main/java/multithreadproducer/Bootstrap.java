package multithreadproducer;

import configs.AppConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Bootstrap {

    private static final Logger logger = LogManager.getLogger();

    public static void main (String [] args) {
        logger.info("Creating Kafka Producer....");

        Properties producerProperties = new Properties();
        KafkaProducer<Integer,String> kafkaProducer=null;
        try
        {
            InputStream inputStream=new FileInputStream(AppConfigs.kafkaProperties);
            producerProperties.load(inputStream);
            producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID_multiThreadProducer);
            producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
            producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            kafkaProducer = new KafkaProducer<Integer,String>(producerProperties);
            Thread [] dispatchers=new Thread[AppConfigs.eventFiles_multiThreadProducer.length];
            logger.info("Starting dispatcher threads...");

            for (int i=0;i<dispatchers.length;i++)
            {
                dispatchers[i]=new Thread(new Dispatcher(kafkaProducer,AppConfigs.topicName_multiThreadProducer,AppConfigs.eventFiles_multiThreadProducer[i]));
                dispatchers[i].start();
            }

            for (Thread t :dispatchers)
            {
                t.join();
            }

        }
        catch (InterruptedException exception)
        {
           logger.error("Interuptted exception.");
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        finally
        {
            kafkaProducer.close();
        }
    }
}
