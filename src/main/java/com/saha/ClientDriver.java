package com.saha;

import com.saha.client.ActivityClient;
import com.saha.model.Activity;

public class ClientDriver {

    public static void main(String[] args) {

        ActivityClient client = new ActivityClient();
        Activity activityById = client.getActivityById(1L);
        System.out.println(activityById.toString());

        System.out.println("/**********************************/");

        client.getAllActivities().forEach(activity -> System.out.println(activity));

    }
}
