package consumer;

import types.CreditCardTxn;
import types.LoggedIn;
import types.NotloggedIn;

public class RecordBuilder {


    public LoggedIn generateLoggedInUserObject(CreditCardTxn ccTxn)
    {
        LoggedIn loggedIn = new LoggedIn(
                ccTxn.getCustomerId(),
                ccTxn.getCustomerType(),
                ccTxn.getCustomerAddress(),
                ccTxn.getCardNo(),
                ccTxn.getTxnId(),
                ccTxn.getTxnAmount(),
                ccTxn.getMerchantId(),
                ccTxn.getMerchantName(),
                ccTxn.getMerchantAddress(),
                true
        );
        return loggedIn;
    }

    public NotloggedIn generateNotLoggedInUserObject(CreditCardTxn ccTxn)
    {
        NotloggedIn notloggedIn = new NotloggedIn(
                ccTxn.getCardNo(),
                ccTxn.getTxnId(),
                ccTxn.getTxnAmount(),
                ccTxn.getMerchantId(),
                ccTxn.getMerchantName(),
                ccTxn.getMerchantAddress()
        );
        return notloggedIn;
    }

    public CreditCardTxn enrich(CreditCardTxn ccTxn)
    {
        if (ccTxn.getCustomerId()==null||ccTxn.getCustomerId().toString().trim().length()<=0)
        {
            ccTxn.setIsLoggedIn(false);
        }
        else
        {
            ccTxn.setIsLoggedIn(true);
        }
        return ccTxn;
    }

}
