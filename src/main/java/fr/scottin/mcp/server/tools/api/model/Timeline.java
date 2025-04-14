package fr.scottin.mcp.server.tools.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Timeline {

    @JsonProperty("type")
    private int type;

    @JsonProperty("code")
    private String code;

    @JsonProperty("longLabel")
    private String longLabel;

    @JsonProperty("country")
    private String country;

    @JsonProperty("shortLabel")
    private String shortLabel;

    @JsonProperty("id")
    private int id;

    @JsonProperty("date")
    private String date;

    @JsonProperty("status")
    private boolean status;
}
