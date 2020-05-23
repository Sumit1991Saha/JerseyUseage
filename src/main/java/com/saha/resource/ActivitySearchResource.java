package com.saha.resource;

import com.saha.model.Activity;
import com.saha.model.ActivitySearch;
import com.saha.repository.ActivityRepository;
import com.saha.repository.ActivityRepositoryStub;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("json/search/activities")
public class ActivitySearchResource {

    private ActivityRepository activityRepository = new ActivityRepositoryStub();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("v1")
    public Response searchActivitiesByDescription(@QueryParam(value = "description") List<String> descriptions) {
        System.out.println("Descriptions :- " + descriptions);

        Response response;
        List<Activity> activities = activityRepository.findByDescriptions(descriptions);
        if (activities.isEmpty()) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            response = Response.ok(activities).build();
        }
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("v2")
    public Response searchActivitiesByDescriptionAndDuration(
            @QueryParam(value = "description") List<String> descriptions,
            @QueryParam(value = "durationFrom") int durationFrom,
            @QueryParam(value = "durationTo") int durationTo) {
        System.out.println("Descriptions :- " + descriptions
                + ", DurationFrom :- " + durationFrom
                + ", DurationTo :- " + durationTo);

        Response response;
        List<Activity> activities = activityRepository.findByDescriptionsAndDuration(descriptions,
                durationFrom, durationTo);
        if (activities.isEmpty()) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            response = Response.ok(activities).build();
        }
        return response;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("v3")
    public Response searchActivitiesBasedOnSearchObject(ActivitySearch activitySearch) {
        System.out.println("Descriptions :- " + activitySearch.getDescriptions()
                + ", DurationFrom :- " + activitySearch.getDurationFrom()
                + ", DurationTo :- " + activitySearch.getDurationTo());

        Response response;
        List<Activity> activities = activityRepository.findByConstraints(activitySearch);
        if (activities.isEmpty()) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            response = Response.ok(activities).build();
        }
        return response;
    }
}
