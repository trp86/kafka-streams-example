package types;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {


      //  CreditCardTxn cxt1 = new CreditCardTxn().newBuilder().build();

        /*CreditCardTxn cxt = new CreditCardTxn (
                "c12",
                "retail",
                new Address(
                        "S12",
                        "pforzheim",
                        "bw",
                        "6576",
                        "2345"
                ),
                true,
                "fsrfs555",
                12345678,
                "665ttww",
                54.98,
                "m1",
                "x trader",
                new Address(
                        "S12",
                        "pforzheim",
                        "bw",
                        "6576",
                        "2345"
                )
        );*/



       //System.out.println("COOKIEE ID IS::-"+ cxt1.getCookieId());

        ObjectMapper mapper = new ObjectMapper();

        CreditCardTxn[] cxt2=mapper.readValue(new File("src/main/resources/data/creditcardtxn.json"),CreditCardTxn[].class);
        CreditCardTxn cxt3 = cxt2[2];

      System.out.println("COOKIEE ID IS::-"+ cxt3.getCustomerType());

      /*  ObjectMapper mapper = new ObjectMapper();

       Address[] adr=mapper.readValue(new File("src/main/resources/data/address.json"),Address[].class);
       Address adr1 = adr[0];
        System.out.println(adr1.getCity());*/

    }


}
