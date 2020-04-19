package streamprocessor;

import configs.AppConfigs;
import types.HadoopRecord;
import types.LineItem;
import types.Notification;
import types.PosInvoice;
import java.util.ArrayList;
import java.util.List;

public class RecordBuilder {

    static List<HadoopRecord> getHadoopRecords(PosInvoice invoice) {
        List<HadoopRecord> records = new ArrayList<>();
        for (LineItem i : invoice.getInvoiceLineItems()) {
            HadoopRecord record = new HadoopRecord(
                    invoice.getInvoiceNumber(),
                    invoice.getCreatedTime(),
                    invoice.getStoreID(),
                    invoice.getPosID(),
                    invoice.getCustomerType(),
                    invoice.getPaymentMethod(),
                    invoice.getDeliveryType(),
                    invoice.getDeliveryAddress().getCity(),
                    invoice.getDeliveryAddress().getState(),
                    invoice.getDeliveryAddress().getPinCode(),
                    i.getItemCode(),
                    i.getItemDescription(),
                    i.getItemPrice(),
                    i.getItemQty(),
                    i.getTotalValue());

            if (invoice.getDeliveryType().toString().equalsIgnoreCase(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY)) {
                record.setCity(invoice.getDeliveryAddress().getCity());
                record.setState(invoice.getDeliveryAddress().getState());
                record.setPinCode(invoice.getDeliveryAddress().getPinCode());
            }
            records.add(record);
        }
        return records;
    }


    static PosInvoice getMaskedInvoice(PosInvoice invoice) {
        invoice.setCustomerCardNo(null);
        if (invoice.getDeliveryType().toString().equalsIgnoreCase(AppConfigs.DELIVERY_TYPE_HOME_DELIVERY)) {
            invoice.getDeliveryAddress().setAddressLine(null);
            invoice.getDeliveryAddress().setContactNumber(null);
        }
        return invoice;
    }

    static Notification getNotification(PosInvoice invoice) {
        return new Notification(
                invoice.getInvoiceNumber(),
                invoice.getCustomerCardNo(),
                invoice.getTotalAmount(),
                invoice.getTotalAmount() * AppConfigs.LOYALITY_FACTOR);
    }
}
