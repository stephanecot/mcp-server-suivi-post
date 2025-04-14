package fr.scottin.mcp.server.tools.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Partner {

    @JsonProperty("numberAVPI")
    private String numberAVPI;
}
