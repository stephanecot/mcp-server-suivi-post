package fr.scottin.mcp.server.test;

import fr.scottin.mcp.server.config.AppPropertiesConfig;
import fr.scottin.mcp.server.tools.McpServerSuiviPostTools;
import fr.scottin.mcp.server.tools.api.model.Event;
import fr.scottin.mcp.server.tools.api.model.Shipment;
import fr.scottin.mcp.server.tools.api.model.ShipmentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class McpServerSuiviPostToolTest {

    @Mock
    private AppPropertiesConfig config;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private McpServerSuiviPostTools serverSuiviPostTools;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        AppPropertiesConfig.AppApiConfig apiConfig = mock(AppPropertiesConfig.AppApiConfig.class);
        when(this.config.getApi()).thenReturn(apiConfig);
        when(apiConfig.url()).thenReturn("https://www.laposte.fr/ssu/sun/back/suivi-unifie/{trackingNumber}");
    }

    @Test
    public void returnsEventWhenTrackingNumberIsValid() {
        ShipmentResponse shipmentResponse = new ShipmentResponse();
        Shipment shipment = new Shipment();
        Event event = new Event();
        shipment.setEvent(Collections.singletonList(event));
        shipmentResponse.setShipment(shipment);

        ResponseEntity<List<ShipmentResponse>> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(responseEntity.getBody()).thenReturn(Collections.singletonList(shipmentResponse));

        when(this.restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class),
                anyString()
        )).thenReturn(responseEntity);

        Event result = this.serverSuiviPostTools.getTrackingStatus("XXXXXXXX");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(event, result);
    }

    @Test
    public void returnsNullWhenApiResponseIsEmpty() {
        ResponseEntity<List<ShipmentResponse>> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(responseEntity.getBody()).thenReturn(Collections.emptyList());

        when(this.restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class),
                anyString()
        )).thenReturn(responseEntity);

        Event result = this.serverSuiviPostTools.getTrackingStatus("EMPTY_RESPONSE");

        Assertions.assertNull(result);
    }

    @Test
    public void returnsNullWhenApiResponseIsNotSuccessful() {
        ResponseEntity<List<ShipmentResponse>> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        when(this.restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                any(ParameterizedTypeReference.class),
                anyString()
        )).thenReturn(responseEntity);

        Event result = this.serverSuiviPostTools.getTrackingStatus("INVALID_TRACKING");

        Assertions.assertNull(result);
    }
}
