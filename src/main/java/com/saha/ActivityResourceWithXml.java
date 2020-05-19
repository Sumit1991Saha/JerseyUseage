package com.saha;

import com.saha.xml.model.Activity;
import com.saha.xml.model.User;
import com.saha.xml.repository.ActivityRepository;
import com.saha.xml.repository.ActivityRepositoryStub;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//http:localhost:8080/JerseyUseage/webapi/xml
@Path("/xml")
public class ActivityResourceWithXml {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/activities")
	public List<Activity> getAllActivities() {
		return activityRepository.findAllActivities();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/activities/{activityId}")
	public Activity getActivity(@PathParam ("activityId") String activityId) {
		return activityRepository.findActivity(activityId);
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/activities/{activityId}/user")
	public User getActivityUser(@PathParam ("activityId") String activityId) {
		Activity activity = activityRepository.findActivity(activityId);
		User user = activity.getUser();
		return user;
	}
	
}
