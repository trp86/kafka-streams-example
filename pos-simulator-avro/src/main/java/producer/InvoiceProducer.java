package producer;


import configs.AppConfigs;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDe;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import types.PosInvoice;

import java.util.Properties;

public class InvoiceProducer {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String [] args)
    {
       /* String topicname=args[0];
        int producerThreads=Integer.valueOf(args[1]);
        int produceSpeed=Integer.valueOf(args[2]);*/

        int producerThreads=2;
        int produceSpeed=10;


        KafkaProducer<String, PosInvoice> kafkaProducer=null;

        try
        {
            Properties producerProps = new Properties();
            producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
            producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
            producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
            producerProps.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, AppConfigs.schemaRegistryURL);

            kafkaProducer = new KafkaProducer<String, PosInvoice>(producerProps);

            Thread [] invoiceDispatchers=new Thread[producerThreads];

            for (int i=0;i<invoiceDispatchers.length;i++)
            {
                invoiceDispatchers[i]=new Thread(new InvoiceDispatcher(AppConfigs.topicName,kafkaProducer,produceSpeed));
                invoiceDispatchers[i].start();
            }


            for (Thread t :invoiceDispatchers)
            {
                t.join();
            }

        }
        catch (InterruptedException exception)
        {
            logger.error("Interuptted exception.");
        }
        finally
        {
            kafkaProducer.close();
        }
    }


}
