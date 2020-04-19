package types;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import configs.AppConfigs;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "InvoiceNumber",
    "CustomerCardNo",
    "TotalAmount",
    "EarnedLoyalityPoints",
    "TotalLoyalityPoints"
})
public class Notification {

    @JsonProperty("InvoiceNumber")
    private String invoiceNumber;
    @JsonProperty("CustomerCardNo")
    private String customerCardNo;
    @JsonProperty("TotalAmount")
    private Double totalAmount;
    @JsonProperty("EarnedLoyalityPoints")
    private Double earnedLoyalityPoints;
    @JsonProperty("TotalLoyalityPoints")
    private Double totalLoyalityPoints;

    @JsonProperty("InvoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("InvoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Notification withInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    @JsonProperty("CustomerCardNo")
    public String getCustomerCardNo() {
        return customerCardNo;
    }

    @JsonProperty("CustomerCardNo")
    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public Notification withCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
        return this;
    }

    @JsonProperty("TotalAmount")
    public Double getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("TotalAmount")
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Notification withTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Notification withEarnedLoyaltyPoints(Double earnedLoyaltyPoints) {
        this.earnedLoyalityPoints = earnedLoyaltyPoints;
        return this;
    }


    @JsonProperty("EarnedLoyalityPoints")
    public Double getEarnedLoyalityPoints() {
        return earnedLoyalityPoints;
    }

    @JsonProperty("EarnedLoyalityPoints")
    public void setEarnedLoyalityPoints(Double earnedLoyalityPoints) {
        this.earnedLoyalityPoints = earnedLoyalityPoints;
    }

    public Notification withEarnedLoyalityPoints(Double earnedLoyalityPoints) {
        this.earnedLoyalityPoints = earnedLoyalityPoints;
        return this;
    }

    @JsonProperty("TotalLoyalityPoints")
    public Double getTotalLoyalityPoints() {
        return totalLoyalityPoints;
    }

    @JsonProperty("TotalLoyalityPoints")
    public void setTotalLoyalityPoints(Double totalLoyalityPoints) {
        this.totalLoyalityPoints = totalLoyalityPoints;
    }

    public Notification withTotalLoyalityPoints(Double totalLoyalityPoints) {
        this.totalLoyalityPoints = totalLoyalityPoints;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("invoiceNumber", invoiceNumber).append("customerCardNo", customerCardNo).append("totalAmount", totalAmount).append("earnedLoyalityPoints", earnedLoyalityPoints).append("totalLoyalityPoints", totalLoyalityPoints).toString();
    }


    public static Notification getNotificationFromInvoice(PosInvoice invoice) {
        return new Notification()
                .withInvoiceNumber(invoice.getInvoiceNumber())
                .withCustomerCardNo(invoice.getCustomerCardNo())
                .withTotalAmount(invoice.getTotalAmount())
                .withEarnedLoyaltyPoints(invoice.getTotalAmount() * AppConfigs.LOYALITY_FACTOR)
                .withTotalLoyalityPoints(invoice.getTotalAmount() * AppConfigs.LOYALITY_FACTOR);
    }

}
