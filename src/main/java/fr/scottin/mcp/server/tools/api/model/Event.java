package fr.scottin.mcp.server.tools.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

    @JsonProperty("code")
    private String code;

    @JsonProperty("type")
    private String type;

    @JsonProperty("group")
    private String group;

    @JsonProperty("label")
    private String label;

    @JsonProperty("date")
    private String date;

    @JsonProperty("country")
    private String country;

    @JsonProperty("order")
    private int order;
}
