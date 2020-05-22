package com.saha.resource;

import com.saha.model.Activity;
import com.saha.repository.ActivityRepository;
import com.saha.repository.ActivityRepositoryStub;
import com.saha.model.User;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//http://localhost:8180/JerseyUseage/webapi/json
@Path("/json/activities")
public class ActivityResourceWithJson {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllActivities() {
		Response response;
		List<Activity> activities = activityRepository.findAllActivities();
		if (activities.isEmpty()) {
			response = Response.status(Response.Status.NO_CONTENT).build();
		} else {
			response = Response.ok().entity(activities).build();
		}
		return response;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Activity createActivity(Activity activity) {
		activityRepository.create(activity);
		return activity;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{activityId}")
	public Response getActivity(@PathParam ("activityId") long activityId) {
		Response response;
		if (activityId == 0) {
			response = Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			Activity activity = activityRepository.findActivity(activityId);
			if (activity == null) {
				response = Response.status(Response.Status.NOT_FOUND).build();
			} else {
				response = Response.ok().entity(activity).build();
			}
		}
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{activityId}/user")
	public User getActivityUser(@PathParam ("activityId") long activityId) {
		Activity activity = activityRepository.findActivity(activityId);
		User user = activity.getUser();
		return user;
	}
	
}
