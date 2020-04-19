package producer;

import config.AppConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import serdes.JsonSerializer;
import serdes.*;
import types.PaymentRequest;

import java.util.Properties;

public class PaymentRequestProducer {

    private static PaymentRequest[] getNextPaymentRequest()
    {
        PaymentRequest[] paymentRequests = new PaymentRequest[4];

        paymentRequests[0] = new PaymentRequest()
                .withAmount(3000.0)
                .withCreatedTime("2019-02-14T13:11:00.00Z")
                .withOTP("852960")
                .withSourceAccountID("131100")
                .withTargetAccountID("151837")
                .withTransactionID("100001");

        paymentRequests[1] = new PaymentRequest()
                .withAmount(2000.0)
                .withCreatedTime("2019-02-14T13:12:00.00Z")
                .withOTP("931749")
                .withSourceAccountID("131200")
                .withTargetAccountID("151837")
                .withTransactionID("100002");

        paymentRequests[2] = new PaymentRequest()
                .withAmount(5000.0)
                .withCreatedTime("2019-02-14T13:13:00.00Z")
                .withOTP("591296")
                .withSourceAccountID("131300")
                .withTargetAccountID("151837")
                .withTransactionID("100003");

        paymentRequests[3] = new PaymentRequest()
                .withAmount(1000.0)
                .withCreatedTime("2019-02-14T13:15:00.00Z")
                .withOTP("283084")
                .withSourceAccountID("131400")
                .withTargetAccountID("151837")
                .withTransactionID("100004");

        /*100001:{"TransactionID": "100001", "CreatedTime": "2019-02-14T13:11:00.00Z", "SourceAccountID": "131100", "TargetAccountID": "151837", "Amount": 3000, "OTP": 852960}
        100002:{"TransactionID": "100002", "CreatedTime": "2019-02-14T13:12:00.00Z", "SourceAccountID": "131200", "TargetAccountID": "151837", "Amount": 2000, "OTP": 931749}
        100003:{"TransactionID": "100003", "CreatedTime": "2019-02-14T13:13:00.00Z", "SourceAccountID": "131300", "TargetAccountID": "151837", "Amount": 5000, "OTP": 591296}
        100004:{"TransactionID": "100004", "CreatedTime": "2019-02-14T13:15:00.00Z", "SourceAccountID": "131400", "TargetAccountID": "151837", "Amount": 1000, "OTP": 283084}
*/

        return paymentRequests;
    }

    public static void main(String[] args) {
        KafkaProducer<String, PaymentRequest> kafkaProducer ;

        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, "SimpleInvoiceProducer");
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        kafkaProducer = new KafkaProducer<String,PaymentRequest>(producerProps);

        PaymentRequest s[] = getNextPaymentRequest();

        for (PaymentRequest i:s)
        {
            kafkaProducer.send(new ProducerRecord<>(AppConfigs.paymentRequestTopicName,i.getTransactionID(),i));
        }

        kafkaProducer.close();

    }

}

