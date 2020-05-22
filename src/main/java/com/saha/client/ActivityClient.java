package com.saha.client;

import com.saha.model.Activity;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class ActivityClient {

    private static final String BASE_URL = "http://localhost:8180/JerseyUseage/webapi/";
    private static final String JSON_RESOURCE_PATH = "json/activities/";

    private final Client client;

    public ActivityClient() {
        client = ClientBuilder.newClient();
    }

    public Activity getActivityById(long id) {

        WebTarget target = client.target(BASE_URL);

        // returns data in json format
        /*System.out.println(target.path(JSON_RESOURCE_PATH + id)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class));*/

        //returns data in object's format
        Activity response = target.path(JSON_RESOURCE_PATH + id)
                .request(MediaType.APPLICATION_JSON)
                .get(Activity.class);

        return response;
    }

    public List<Activity> getAllActivities() {

        WebTarget target = client.target(BASE_URL);

        //returns data in object's format
        List<Activity> response = target.path(JSON_RESOURCE_PATH)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Activity>>(){});

        return response;
    }
}
