package com.saha;

import com.saha.client.ActivityClient;
import com.saha.model.Activity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ClientDriver {

    private ActivityClient client;

    @Before
    public void setup() {
        client = new ActivityClient();
    }

    @Before
    public void teardown() {
        System.out.println("/*********/");
    }

    @Test
    public void testGetByIdvalidIdScenario() {
        System.out.println("testGetByIdvalidIdScenario :- ");
        Activity activity = client.getActivityById(1L);
        System.out.println(activity.toString());
        Assert.assertNotNull(activity);
    }

    @Test
    public void testGetByIdInalidIdScenario() {
        System.out.println("testGetByIdInalidIdScenario");
        Activity activity = client.getActivityById(100L);
        Assert.assertNull(activity);
    }

    @Test
    public void testGetAllValidIdScenario() {
        System.out.println("testGetAllValidIdScenario :- ");

        List<Activity> activities = client.getAllActivities();
        activities.forEach(activity -> System.out.println(activity));

        Assert.assertNotNull(activities);
    }

    @Test
    public void testPostActivity() {
        System.out.println("testPostActivity :- ");

        Activity activity = new Activity();
        activity.setDescription("Jogging");
        activity.setDuration(45);

        Activity createdActivity = client.createActivity(activity);
        System.out.println(createdActivity.toString());
        Assert.assertNotNull(createdActivity);
    }

    @Test
    public void testCreateWithUpdateActivity() {
        System.out.println("testCreateWithUpdateActivity :- ");

        ActivityClient client = new ActivityClient();
        Activity activity = new Activity();
        activity.setDescription("Tennis");
        activity.setDuration(45);

        Activity createdActivity = client.createActivity(activity);
        System.out.println(createdActivity.toString());

        int updatedDuration = 130;
        createdActivity.setDuration(updatedDuration);
        client.updateActivity(createdActivity.getId(), createdActivity);
        Activity updatedActivity = client.getActivityById(createdActivity.getId());
        System.out.println(updatedActivity.toString());

        Assert.assertNotNull(updatedActivity);
        Assert.assertEquals(updatedDuration, updatedActivity.getDuration());
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidUpdateActivity() {
        System.out.println("testInvalidUpdateActivity :- ");

        Activity activity = new Activity();
        activity.setDescription("Tennis");
        activity.setDuration(45);

        client.updateActivity(100, activity);
    }

    @Test
    public void testDeleteActivity() {
        System.out.println("testDeleteActivity :- ");
        int idOfActivityToBeDeleted = 1;
        client.deleteActivity(idOfActivityToBeDeleted);

        Activity activity = client.getActivityById(idOfActivityToBeDeleted);

        Assert.assertNull(activity);
    }
}
