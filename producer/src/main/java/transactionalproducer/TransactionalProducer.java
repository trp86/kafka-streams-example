package transactionalproducer;

import configs.AppConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;


public class TransactionalProducer {

    private static final Logger logger = LogManager.getLogger();

    public static void main (String [] args)
    {
        logger.info("Creating Kafka Producer....");
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        producerProperties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,AppConfigs.transactionID);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer,String> producer=new KafkaProducer<Integer, String>(producerProperties);

        try
        {
            //Initialize transactions
            producer.initTransactions();

            //FIRST TRANSACTION - START
            //Begin transaction
            producer.beginTransaction();

            for (int i=0;i<=AppConfigs.numEvents_transactionalProducer;i++)
            {
                producer.send(new ProducerRecord<>(AppConfigs.topicName1,i,"SimpleMessage-T1-:-"+i));
                producer.send(new ProducerRecord<>(AppConfigs.topicName2,i,"SimpleMessage-T1-:-"+i));
            }

            //Commit transaction
            producer.commitTransaction();
            //FIRST TRANSACTION - END


            //RollBack Scenario
            //SECOND TRANSACTION - START
            //Begin transaction
            producer.beginTransaction();

            for (int i=0;i<=AppConfigs.numEvents_transactionalProducer;i++)
            {
                producer.send(new ProducerRecord<>(AppConfigs.topicName1,i,"SimpleMessage-T2-:-"+i));
                producer.send(new ProducerRecord<>(AppConfigs.topicName2,i,"SimpleMessage-T2-:-"+i));
            }

            //Abort transaction manually.
            //Messages should not be produced as we are not commiting the transaction
            producer.abortTransaction();

            //SECOND TRANSACTION - END
        }

        catch(Exception e)
        {
            //Exception occured.Abort the transaction
            producer.abortTransaction();
            throw new RuntimeException(e);
        }

        finally
        {
            producer.close();
        }
        logger.info("Message producing completed...");







    }

}

