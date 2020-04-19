package bootstrap;

import types.PaymentConfirmation;
import types.PaymentRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import java.time.Instant;

public class AppTimeExtractor {

    private static final class PaymentRequestTimeExtractor implements TimestampExtractor {

        @Override
        public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
            PaymentRequest record = (PaymentRequest) consumerRecord.value();
            long eventTime = Instant.parse(record.getCreatedTime()).toEpochMilli();
            return ((eventTime > 0) ? eventTime : prevTime);
        }
    }

    public static PaymentRequestTimeExtractor PaymentRequest() {
        return new PaymentRequestTimeExtractor();
    }

    private static final class PaymentConfirmationTimeExtractor implements TimestampExtractor {

        @Override
        public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
            PaymentConfirmation record = (PaymentConfirmation) consumerRecord.value();
            long eventTime = Instant.parse(record.getCreatedTime()).toEpochMilli();
            return ((eventTime > 0) ? eventTime : prevTime);
        }
    }

    public static PaymentConfirmationTimeExtractor PaymentConfirmation() {
        return new PaymentConfirmationTimeExtractor();
    }

}
