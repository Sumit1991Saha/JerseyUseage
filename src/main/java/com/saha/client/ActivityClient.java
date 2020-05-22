package com.saha.client;

import com.saha.model.Activity;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        Response response = target.path(JSON_RESOURCE_PATH + id)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Error Ocurred :- " + response.getStatus());
        }

        return response.readEntity(Activity.class);
    }

    public List<Activity> getAllActivities() {

        WebTarget target = client.target(BASE_URL);

        //returns data in object's format
        Response response = target.path(JSON_RESOURCE_PATH)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.getStatus() + " Error Ocurred");
        }

        return response.readEntity(new GenericType<List<Activity>>(){});
    }

    public Activity createActivity(Activity activity) {
        WebTarget target = client.target(BASE_URL);

        //returns data in object's format
        Response response = target.path(JSON_RESOURCE_PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(activity, MediaType.APPLICATION_JSON));
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.getStatus() + " Error Ocurred");
        }

        return response.readEntity(Activity.class);
    }

    public Activity updateActivity(long id, Activity activity) {
        WebTarget target = client.target(BASE_URL);

        //returns data in object's format
        Response response = target.path(JSON_RESOURCE_PATH + id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(activity, MediaType.APPLICATION_JSON));
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.getStatus() + " Error Ocurred");
        }

        return response.readEntity(Activity.class);
    }
}
