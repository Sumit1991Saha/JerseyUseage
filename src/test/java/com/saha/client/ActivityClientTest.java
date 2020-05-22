package com.saha.client;

import com.saha.model.Activity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ActivityClientTest {

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
    public void testGetByIdInvalidIdScenario() {
        Activity activity = client.getActivityById(1L);
        System.out.println("testGetByIdValidIdScenario :- " + activity.toString());
        Assert.assertNotNull(activity);
    }

    @Test (expected = RuntimeException.class)
    public void testGetByIdValidIdScenario() {
        System.out.println("testGetByIdValidIdScenario");
        client.getActivityById(100L);
    }

    @Test
    public void testGetAllValidIdScenario() {
        System.out.println("testGetAllValidIdScenario :- ");

        List<Activity> activities = client.getAllActivities();
        activities.forEach(activity -> System.out.println(activity));

        Assert.assertNotNull(activities);
    }
}
