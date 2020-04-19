package producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import types.PosInvoice;

import java.io.File;
import java.io.IOException;

public class Test {


    public static void main(String[] args) throws IOException {

        ObjectMapper mapper= new ObjectMapper();
        String DATAFILE = "src/main/resources/data/Invoice.json";

        PosInvoice[] invoices = mapper.readValue(new File(DATAFILE), PosInvoice[].class);
        PosInvoice invoice = invoices[0];

        System.out.println(invoice.getCustomerCardNo());
    }

}
