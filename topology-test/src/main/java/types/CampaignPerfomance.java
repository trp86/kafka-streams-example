package types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Campaigner",
        "AdImpressions",
        "AdClicks"
})
public class CampaignPerfomance {

    @JsonProperty("Campaigner")
    private String campaigner;
    @JsonProperty("AdImpressions")
    private Long adImpressions;
    @JsonProperty("AdClicks")
    private Long adClicks;

    @JsonProperty("Campaigner")
    public String getCampaigner() {
        return campaigner;
    }

    @JsonProperty("Campaigner")
    public void setCampaigner(String campaigner) {
        this.campaigner = campaigner;
    }

    public CampaignPerfomance withCampaigner(String campaigner) {
        this.campaigner = campaigner;
        return this;
    }

    @JsonProperty("AdImpressions")
    public Long getAdImpressions() {
        return adImpressions;
    }

    @JsonProperty("AdImpressions")
    public void setAdImpressions(Long adImpressions) {
        this.adImpressions = adImpressions;
    }

    public CampaignPerfomance withAdImpressions(Long adImpressions) {
        this.adImpressions = adImpressions;
        return this;
    }

    @JsonProperty("AdClicks")
    public Long getAdClicks() {
        return adClicks;
    }

    @JsonProperty("AdClicks")
    public void setAdClicks(Long adClicks) {
        this.adClicks = adClicks;
    }

    public CampaignPerfomance withAdClicks(Long adClicks) {
        this.adClicks = adClicks;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("campaigner", campaigner).append("adImpressions", adImpressions).append("adClicks", adClicks).toString();
    }

}