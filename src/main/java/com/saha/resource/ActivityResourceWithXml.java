package com.saha.resource;

import com.saha.repository.ActivityRepository;
import com.saha.model.Activity;
import com.saha.model.User;
import com.saha.repository.ActivityRepositoryStub;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//http://localhost:8180/JerseyUseage/webapi/xml
@Path("/xml/activities")
public class ActivityResourceWithXml {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Activity> getAllActivities() {
		return activityRepository.findAllActivities();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{activityId}")
	public Activity getActivity(@PathParam ("activityId") long activityId) {
		return activityRepository.findActivity(activityId);
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{activityId}/user")
	public User getActivityUser(@PathParam ("activityId") long activityId) {
		Activity activity = activityRepository.findActivity(activityId);
		User user = activity.getUser();
		return user;
	}
}
