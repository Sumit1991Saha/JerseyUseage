package com.saha;

import com.saha.client.ActivityClient;
import com.saha.model.Activity;

public class ClientDriver {

    public static void main(String[] args) {

        //First Sample
        ActivityClient client = new ActivityClient();
        Activity activityById = client.getActivityById(1L);
        System.out.println(activityById.toString());

        System.out.println("/**********************************/");

        //Second Sample
        client.getAllActivities().forEach(activity -> System.out.println(activity));

        System.out.println("/**********************************/");

        //Third Sample
        //Error Scenario
        try {
            client.getActivityById(100L);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
