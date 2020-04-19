package datagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import types.Address;
import types.CreditCardTxn;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GenerateCreditCardTxn {

    private static final Logger logger = LogManager.getLogger();
    private static  GenerateCreditCardTxn generateCreditCardTxn=null;
    private ObjectMapper mapper;
    private final CreditCardTxn[] creditCardTxns;
    private final Address[] addresses;
    private Random rand = new Random();

    public static GenerateCreditCardTxn getInstance() throws IOException {
        if (generateCreditCardTxn==null)
        {
            generateCreditCardTxn=new GenerateCreditCardTxn();
        }
        return generateCreditCardTxn;
    }

    private GenerateCreditCardTxn() throws IOException {
        mapper = new ObjectMapper();
        creditCardTxns=mapper.readValue(new File("src/main/resources/data/creditcardtxn.json"),CreditCardTxn[].class);
        addresses=mapper.readValue(new File("src/main/resources/data/address.json"),Address[].class);
    }

    public CreditCardTxn generateNextTxn()
    {
        CreditCardTxn creditCardTxn = creditCardTxns[rand.nextInt(10)];
        creditCardTxn.setMerchantAddress(addresses[rand.nextInt(5)]);
        creditCardTxn.setCustomerAddress(addresses[rand.nextInt(5)]);
        return creditCardTxn;
    }

}
