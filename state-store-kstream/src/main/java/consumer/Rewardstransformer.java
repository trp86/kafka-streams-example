package consumer;

import configs.AppConfigs;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import types.Notification;
import types.PosInvoice;

public class Rewardstransformer implements ValueTransformer<PosInvoice, Notification> {

    private KeyValueStore<String,Double> stateStore;

    @Override
    public void init(ProcessorContext processorContext) {
        this.stateStore= (KeyValueStore<String, Double>) processorContext.getStateStore(AppConfigs.REWARDS_STORE_NAME);
    }

    @Override
    public Notification transform(PosInvoice posInvoice) {
        Notification notification = new Notification()
                .withInvoiceNumber(posInvoice.getInvoiceNumber())
                .withCustomerCardNo(posInvoice.getCustomerCardNo())
                .withTotalAmount(posInvoice.getTotalAmount())
                .withEarnedLoyalityPoints(posInvoice.getTotalAmount()*AppConfigs.LOYALITY_FACTOR)
                .withTotalLoyalityPoints(0.0);
        Double totalRewards;
        Double accumulatedRewards=stateStore.get(notification.getCustomerCardNo());

        if (accumulatedRewards!=null)
        {
            totalRewards=accumulatedRewards+notification.getEarnedLoyalityPoints();
        }
        else
        {
            totalRewards=notification.getEarnedLoyalityPoints();
        }

        stateStore.put(notification.getCustomerCardNo(),totalRewards);
        notification.setEarnedLoyalityPoints(totalRewards);

        return notification;
    }

    @Override
    public void close() {

    }
}
