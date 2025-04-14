package fr.scottin.mcp.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppPropertiesConfig {

    public record AppApiConfig(String url) {}

    private AppApiConfig api;
}
