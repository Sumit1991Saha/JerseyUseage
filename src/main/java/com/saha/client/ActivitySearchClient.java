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
    private static final String JSON_RESOURCE_PATH = "json/search/activities/";
    private static final String V1 = "v1";
    private static final String V2 = "v2";

    private final Client client;

    public ActivitySearchClient() {
        client = ClientBuilder.newClient();
    }

    public List<Activity> searchActivitiesBasedOnDescription(String param, String[] searchVales) {

        //http://localhost:8180/JerseyUseage/webapi/json/search/activities?description=Swimming&description=Jogging
        //sample uri build for the data passes by the junit tests
        URI uri = UriBuilder.fromUri(BASE_URL)
                .path(JSON_RESOURCE_PATH + V1)
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

    public List<Activity> searchActivitiesBasedOnDescriptionAndDuration(String firstParam, String[] searchVales,
                                                                        String secondParam, int durationFrom,
                                                                        String thirdParam, int durationTo) {

        //http://localhost:8180/JerseyUseage/webapi/json/search/activities?description=Swimming&description=Jogging&durationFrom=durationFrom&durationTo=durationTo
        //sample uri build for the data passes by the junit tests
        URI uri = UriBuilder.fromUri(BASE_URL)
                .path(JSON_RESOURCE_PATH + V2)
                .queryParam(firstParam, searchVales)
                .queryParam(secondParam, durationFrom)
                .queryParam(thirdParam, durationTo)
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
