package com.saha;

import com.saha.client.ActivityClient;
import com.saha.client.ActivitySearchClient;
import com.saha.model.Activity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientDriver {

    private ActivityClient activityClient;
    private ActivitySearchClient activitySearchClient;

    @Before
    public void setup() {
        activityClient = new ActivityClient();
        activitySearchClient = new ActivitySearchClient();
    }

    @After
    public void teardown() {
        System.out.println("/*********/");
    }

    @Test
    public void testGetByIdValidIdScenario() {
        System.out.println("testGetByIdValidIdScenario :- ");
        Activity activity = activityClient.getActivityById(1L);
        System.out.println(activity.toString());
        Assert.assertNotNull(activity);
    }

    @Test
    public void testGetByIdInvalidIdScenario() {
        System.out.println("testGetByIdInvalidIdScenario");
        Activity activity = activityClient.getActivityById(100L);
        Assert.assertNull(activity);
    }

    @Test
    public void testGetAllValidIdScenario() {
        System.out.println("testGetAllValidIdScenario :- ");

        List<Activity> activities = activityClient.getAllActivities();
        activities.forEach(activity -> System.out.println(activity));

        Assert.assertNotNull(activities);
    }

    @Test
    public void testPostActivity() {
        System.out.println("testPostActivity :- ");

        Activity activity = new Activity();
        activity.setDescription("Jogging");
        activity.setDuration(45);

        Activity createdActivity = activityClient.createActivity(activity);
        System.out.println(createdActivity.toString());
        Assert.assertNotNull(createdActivity);

        activityClient.deleteActivity(createdActivity.getId());
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

        activityClient.updateActivity(100, activity);
    }

    @Test
    public void testDeleteActivity() {
        System.out.println("testDeleteActivity :- ");

        Activity activity = new Activity();
        activity.setDescription("Jogging");
        activity.setDuration(45);

        Activity createdActivity = activityClient.createActivity(activity);

        long idOfActivityToBeDeleted = createdActivity.getId();
        activityClient.deleteActivity(idOfActivityToBeDeleted);

        Assert.assertNull(activityClient.getActivityById(idOfActivityToBeDeleted));
    }

    @Test
    public void testSearchActivitiesUsingSingleQueryParam() {
        System.out.println("testSearchActivitiesUsingSingleQueryParam :- ");

        String param = "description";
        String[] searchVales = {"Swimming", "Jogging"};
        Set<String> descriptions = new HashSet<>(Arrays.asList(searchVales));

        List<Activity> activities = activitySearchClient.searchActivitiesBasedOnDescription(param, searchVales);
        System.out.println(activities);

        Assert.assertEquals(searchVales.length, activities.size());
        activities.forEach(activity -> Assert.assertTrue(descriptions.contains(activity.getDescription())));
    }

    @Test
    public void testSearchActivitiesUsingMultipleQueryParam() {
        System.out.println("testSearchActivitiesUsingMultipleQueryParam :- ");

        String firstParam = "description";
        String[] searchVales = {"Swimming", "Jogging"};

        String secondParam = "durationFrom";
        int durationFrom = 60;

        String thirdParam = "durationTo";
        int durationTo = 130;

        //http://localhost:8180/JerseyUseage/webapi/json/search/activities?description=Swimming&description=Jogging&durationFrom=durationFrom&durationTo=durationTo
        List<Activity> activities = activitySearchClient.searchActivitiesBasedOnDescriptionAndDuration(firstParam, searchVales, secondParam, durationFrom, thirdParam, durationTo);
        System.out.println(activities);

        Assert.assertEquals(1, activities.size());
        Assert.assertEquals(searchVales[0], activities.get(0).getDescription());
    }
}
