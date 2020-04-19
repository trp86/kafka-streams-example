package producer;

import config.AppConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import serdes.JsonSerializer;
import types.PaymentConfirmation;
import java.util.Properties;

public class PaymentConfirmationProducer {

    private static PaymentConfirmation[] getNextPaymentConfirmation()
    {
        PaymentConfirmation[] paymentConfirmations = new PaymentConfirmation[3];

        paymentConfirmations[0] = new PaymentConfirmation()
                .withCreatedTime("2019-02-14T13:15:00.00Z")
                .withOTP("852960")
                .withTransactionID("100001");

        paymentConfirmations[1] = new PaymentConfirmation()
                .withCreatedTime("2019-02-14T13:18:00.00Z")
                .withOTP("931749")
                .withTransactionID("100002");

        paymentConfirmations[2] = new PaymentConfirmation()
                .withCreatedTime("2019-02-14T13:14:00.00Z")
                .withOTP("283086")
                .withTransactionID("100004");

        return paymentConfirmations;
    }

    public static void main(String[] args) {
        KafkaProducer<String, PaymentConfirmation> kafkaProducer ;

        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, "SimpleInvoiceProducer");
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        kafkaProducer = new KafkaProducer<String,PaymentConfirmation>(producerProps);

        PaymentConfirmation s[] = getNextPaymentConfirmation();

        for (PaymentConfirmation i:s)
        {
            kafkaProducer.send(new ProducerRecord<>(AppConfigs.paymentConfirmationTopicName,i.getTransactionID(),i));
        }

        kafkaProducer.close();

    }

}

