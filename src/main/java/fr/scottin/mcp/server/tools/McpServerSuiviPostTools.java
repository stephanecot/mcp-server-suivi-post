package fr.scottin.mcp.server.tools;

import fr.scottin.mcp.server.config.AppPropertiesConfig;
import fr.scottin.mcp.server.tools.api.model.Event;
import fr.scottin.mcp.server.tools.api.model.ShipmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class McpServerSuiviPostTools {

    private static final String USER_AGENT = "User-Agent";
    private static final String USER_AGENT_UA = "CustomUserAgent/1.0";
    private static final String ACCEPT_LANGUAGE = "Accept-Language";
    private static final String ACCEPT_LANGUAGE_FR = "fr-FR";

    private final AppPropertiesConfig config;

    private final RestTemplate restTemplate;

    @Tool(description = "Find details about a french parcel by its tracking number. It's managed by the french post office. It's named colissimo.")
    public Event getTrackingStatus(@ToolParam(description = "The tracking number") String trackingNumber) {

        ShipmentResponse response = this.getShipmentResponse(trackingNumber);

        return Optional.ofNullable(response).map(shipmentResponse -> {
                List<Event> events = shipmentResponse.getShipment().getEvent();
                events.sort(Comparator.comparing(Event::getDate).reversed()); // Get the most recent event first

                return events.getFirst();
            }).orElse(null);
    }

    private ShipmentResponse getShipmentResponse(String trackingNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(USER_AGENT, USER_AGENT_UA);
        headers.set(ACCEPT_LANGUAGE, ACCEPT_LANGUAGE_FR);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        var response = this.restTemplate.exchange(
                this.config.getApi().url(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<ShipmentResponse>>() {},
                trackingNumber
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        List<ShipmentResponse> body = response.getBody();
        if (Objects.requireNonNull(body).isEmpty()) {
            return null;

        } else {
            return body.getFirst();
        }
    }
}
