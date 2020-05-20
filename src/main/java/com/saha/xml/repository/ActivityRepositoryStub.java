package com.saha.xml.repository;

import com.saha.xml.model.Activity;
import com.saha.xml.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActivityRepositoryStub implements ActivityRepository {

	private static Map<Long, Activity> activitiesMap = new HashMap<>();
	private static long idCount = 1L;
	static {
		Activity activity1 = new Activity();
		activity1.setId(idCount++);
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		User user1 = new User();
		user1.setId(5678L);
		user1.setName("Sumit");
		activity1.setUser(user1);
		activitiesMap.put(activity1.getId(), activity1);

		Activity activity2 = new Activity();
		activity2.setId(idCount++);
		activity2.setDescription("Cycling");
		activity2.setDuration(120);
		User user2 = new User();
		user2.setId(4763L);
		user2.setName("Saha");
		activity2.setUser(user2);
		activitiesMap.put(activity2.getId(), activity2);
	}
	public List<Activity> findAllActivities () {
		return new ArrayList<>(activitiesMap.values());
	}
	
	@Override
	public Activity findActivity(long activityId) {
		return activitiesMap.get(activityId);
	}

	@Override
	public void create(Activity activity) {
		long currentIdCount = idCount++;
		activity.setId(currentIdCount);
		activitiesMap.put(currentIdCount, activity);
	}
}
