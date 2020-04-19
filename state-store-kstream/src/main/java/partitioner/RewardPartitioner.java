package partitioner;

import org.apache.kafka.streams.processor.StreamPartitioner;
import types.PosInvoice;

public class RewardPartitioner implements StreamPartitioner<String, PosInvoice> {


    @Override
    public Integer partition(String topic, String key, PosInvoice value, int numberOfPartitions) {
        return value.getCustomerCardNo().hashCode()%numberOfPartitions;
    }
}
