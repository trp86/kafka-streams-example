package supressingwindow;

import types.HeartBeat;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import java.time.Instant;

public class AppTimestampExtractor implements TimestampExtractor{

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        HeartBeat record = (HeartBeat) consumerRecord.value();
        long eventTime = Instant.parse(record.getCreatedTime()).toEpochMilli();
        return ((eventTime > 0) ? eventTime : prevTime);

    }

}
