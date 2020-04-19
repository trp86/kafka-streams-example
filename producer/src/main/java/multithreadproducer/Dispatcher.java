package multithreadproducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Scanner;

public class Dispatcher implements Runnable{
    private static final Logger logger =LogManager.getLogger();
    private String fileLocation;
    private String topicName;
    private KafkaProducer<Integer,String> kafkaProducer;

    Dispatcher(KafkaProducer<Integer,String> kafkaProducer,
                      String topicName,
                      String fileLocation) {
        this.topicName=topicName;
        this.kafkaProducer=kafkaProducer;
        this.fileLocation = fileLocation;
    }

    @Override
    public void run() {

        try
        {
            logger.info("Start Processing::-"+fileLocation);
            File file= new File(fileLocation);
            Scanner scanner=new Scanner(file);
            int counter=0;

            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                kafkaProducer.send(new ProducerRecord<>(topicName,null,line));
                counter++;
            }
            logger.info("Finished sending "+counter+" messages from "+fileLocation);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

    }
}
