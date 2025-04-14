package fr.scottin.mcp.server.tools.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContextData {

    @JsonProperty("isParcelBack")
    private boolean isParcelBack;

    @JsonProperty("merchantName")
    private String merchantName;

    @JsonProperty("merchantLogo")
    private String merchantLogo;

    @JsonProperty("customPaid")
    private boolean customPaid;

    @JsonProperty("geographicCoverage")
    private String geographicCoverage;

    @JsonProperty("deliveryPicto")
    private int deliveryPicto;

    @JsonProperty("originCountry")
    private String originCountry;

    @JsonProperty("arrivalCountry")
    private String arrivalCountry;

    @JsonProperty("checkCustoms")
    private boolean checkCustoms;

    @JsonProperty("customCTA")
    private String customCTA;

    @JsonProperty("energyClassCode")
    private String energyClassCode;

    @JsonProperty("deliveryChoice")
    private DeliveryChoice deliveryChoice;

    @JsonProperty("partner")
    private Partner partner;
}
