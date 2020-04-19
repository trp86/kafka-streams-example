package sessionwindow;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import types.UserClicks;
import java.time.Instant;

public class AppTimestampExtractor implements TimestampExtractor{

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        UserClicks record = (UserClicks) consumerRecord.value();
        long eventTime = Instant.parse(record.getCreatedTime()).toEpochMilli();
        return ((eventTime > 0) ? eventTime : prevTime);

    }

}
