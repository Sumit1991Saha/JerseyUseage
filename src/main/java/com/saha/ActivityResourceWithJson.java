package com.saha;

import com.saha.xml.model.Activity;
import com.saha.xml.model.User;
import com.saha.xml.repository.ActivityRepository;
import com.saha.xml.repository.ActivityRepositoryStub;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//http:localhost:8080/JerseyUseage/webapi/json
@Path("/json/activities")
public class ActivityResourceWithJson {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Activity> getAllActivities() {
		return activityRepository.findAllActivities();
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
	public Activity getActivity(@PathParam ("activityId") long activityId) {
		return activityRepository.findActivity(activityId);
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
