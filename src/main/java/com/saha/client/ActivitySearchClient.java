package com.saha.client;

import com.saha.model.Activity;

import java.net.URI;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class ActivitySearchClient {

    private static final String BASE_URL = "http://localhost:8180/JerseyUseage/webapi/";
    private static final String JSON_RESOURCE_PATH = "json/search/activities";

    private final Client client;

    public ActivitySearchClient() {
        client = ClientBuilder.newClient();
    }

    public List<Activity> searchActivities(String param, String[] searchVales) {

        //http://localhost:8180/JerseyUseage/webapi/json/search/activities?description=Swimming&description=Jogging
        //sample uri build for the data passes by the junit tests
        URI uri = UriBuilder.fromUri(BASE_URL)
                .path(JSON_RESOURCE_PATH)
                .queryParam(param, searchVales)
                .build();

        WebTarget target = client.target(uri);

        //returns data in object's format
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.getStatus() + " Error Ocurred");
        }

        return response.readEntity(new GenericType<List<Activity>>(){});
    }
}
