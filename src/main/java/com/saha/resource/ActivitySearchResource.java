package com.saha.resource;

import com.saha.model.Activity;
import com.saha.repository.ActivityRepository;
import com.saha.repository.ActivityRepositoryStub;

import java.util.List;
import javax.ws.rs.GET;
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
    public Response searchActivities(@QueryParam(value = "description") List<String> descriptions) {
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
}
