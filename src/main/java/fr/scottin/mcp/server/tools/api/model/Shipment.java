package fr.scottin.mcp.server.tools.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Shipment {

    @JsonProperty("idShip")
    private String idShip;

    @JsonProperty("systemOwner")
    private int systemOwner;

    @JsonProperty("holder")
    private int holder;

    @JsonProperty("product")
    private String product;

    @JsonProperty("isFinal")
    private boolean isFinal;

    @JsonProperty("deliveryDate")
    private String deliveryDate;

    @JsonProperty("entryDate")
    private String entryDate;

    @JsonProperty("timeline")
    private List<Timeline> timeline;

    @JsonProperty("productCode")
    private String productCode;

    @JsonProperty("estimDate")
    private String estimDate;

    @JsonProperty("event")
    private List<Event> event;

    @JsonProperty("contextData")
    private ContextData contextData;

    @JsonProperty("url")
    private String url;
}
