package producer;

import datagenerator.GenerateCreditCardTxn;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import config.AppConfigs;

import java.io.IOException;
import java.util.Properties;
import types.CreditCardTxn;

public class CreditCardTxnProducer {




    public static void main(String[] args) throws IOException {

        int numEvents=100000;

        GenerateCreditCardTxn generateCreditCardTxn=GenerateCreditCardTxn.getInstance();




        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        producerProps.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, AppConfigs.schemaRegistryURL);

        KafkaProducer<String, CreditCardTxn> kafkaProducer = new  KafkaProducer<String, CreditCardTxn>(producerProps);

        for (int i=0;i<numEvents;i++)
        {
            CreditCardTxn cxn = generateCreditCardTxn.generateNextTxn();
            kafkaProducer.send(new ProducerRecord<>(AppConfigs.topicName,
                    cxn.getCustomerId().toString(),
                    cxn));

        }


    }

}
