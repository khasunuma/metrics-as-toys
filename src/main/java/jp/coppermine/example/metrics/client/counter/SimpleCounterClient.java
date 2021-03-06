package jp.coppermine.example.metrics.client.counter;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_METRICS;
import static jp.coppermine.example.metrics.client.Endpoint.APPLICATION_PATH;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Path("counter")
@RequestScoped
public class SimpleCounterClient {

    @GET
    public String getValue() {
        Client client = ClientBuilder.newClient();
        
        client.target(APPLICATION_PATH).path("counter").path("inc").request().get();
        
        JsonResponse response = client.target(APPLICATION_METRICS).path("counter").request(APPLICATION_JSON).get(JsonResponse.class);
        
        return String.format("[METRICS] counter: %d", response.getValue());
    }
    
}
