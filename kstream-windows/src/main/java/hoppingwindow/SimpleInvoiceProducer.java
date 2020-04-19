package hoppingwindow;

import configs.AppConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import serdes.JsonSerializer;
import types.SimpleInvoice;

import java.util.Properties;

public class SimpleInvoiceProducer {

    private static SimpleInvoice[] getNextInvoice()
    {

        SimpleInvoice [] si = new SimpleInvoice [7];

        si[0]=new SimpleInvoice().
                withInvoiceNumber("101").withCreatedTime("2019-02-05T10:00:10.00Z").
                withStoreID("STR1534").withTotalAmount(1920.0);
        si[1]=new SimpleInvoice().
                withInvoiceNumber("102").withCreatedTime("2019-02-05T10:00:40.00Z").
                withStoreID("STR1535").withTotalAmount(1860.0);
        si[2]=new SimpleInvoice().
                withInvoiceNumber("103").withCreatedTime("2019-02-05T10:01:11.00Z").
                withStoreID("STR1534").withTotalAmount(2400.0);
        si[3]=new SimpleInvoice().
                withInvoiceNumber("104").withCreatedTime("2019-02-05T10:02:11.00Z").
                withStoreID("STR1535").withTotalAmount(8936.0);
        si[4]=new SimpleInvoice().
                withInvoiceNumber("105").withCreatedTime("2019-02-05T10:03:15.00Z").
                withStoreID("STR1535").withTotalAmount(6375.0);
        si[5]=new SimpleInvoice().
                withInvoiceNumber("106").withCreatedTime("2019-02-05T10:04:12.00Z").
                withStoreID("STR1534").withTotalAmount(9365.0);
        si[6]=new SimpleInvoice().
                withInvoiceNumber("107").withCreatedTime("2019-02-05T10:07:11.00Z").
                withStoreID("STR1534").withTotalAmount(5276.0);


        /*STR1534:{"InvoiceNumber": 101,"CreatedTime": "2019-02-05T10:00:10.00Z","StoreID": "STR1534","TotalAmount": 1920}
        STR1535:{"InvoiceNumber": 102,"CreatedTime": "2019-02-05T10:00:40.00Z","StoreID": "STR1534","TotalAmount": 1860}
        STR1534:{"InvoiceNumber": 103,"CreatedTime": "2019-02-05T10:01:11.00Z","StoreID": "STR1534","TotalAmount": 2400}
        STR1535:{"InvoiceNumber": 104,"CreatedTime": "2019-02-05T10:02:11.00Z","StoreID": "STR1534","TotalAmount": 8936}
        STR1535:{"InvoiceNumber": 105,"CreatedTime": "2019-02-05T10:03:15.00Z","StoreID": "STR1534","TotalAmount": 6375}
        STR1534:{"InvoiceNumber": 106,"CreatedTime": "2019-02-05T10:04:12.00Z","StoreID": "STR1534","TotalAmount": 9365}
        STR1534:{"InvoiceNumber": 107,"CreatedTime": "2019-02-05T10:07:11.00Z","StoreID": "STR1534","TotalAmount": 5276}*/

        return si;
    }

    public static void main(String[] args) {
        KafkaProducer<String, SimpleInvoice> kafkaProducer = null;


        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, "SimpleInvoiceProducer");
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.bootstrapServers);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        kafkaProducer = new KafkaProducer<String,SimpleInvoice>(producerProps);

        SimpleInvoice s[] = getNextInvoice();

        //System.out.println(s);

        for (SimpleInvoice i:s)
        {
            kafkaProducer.send(new ProducerRecord<>(AppConfig.posTopicName,i.getStoreID(),i));
        }

        kafkaProducer.close();

    }



}
