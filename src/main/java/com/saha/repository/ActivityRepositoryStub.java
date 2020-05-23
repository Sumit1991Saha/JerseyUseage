package com.saha.repository;

import com.saha.model.Activity;
import com.saha.model.ActivitySearch;
import com.saha.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActivityRepositoryStub implements ActivityRepository {

	private static Map<Long, Activity> activitiesMap = new HashMap<>();
	private static long idCount = 1L;
	static {
		Activity activity1 = new Activity();
		activity1.setId(idCount++);
		activity1.setDescription("Swimming");
		activity1.setDuration(70);
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

		Activity activity3 = new Activity();
		activity3.setId(idCount++);
		activity3.setDescription("Jogging");
		activity3.setDuration(50);
		activitiesMap.put(activity3.getId(), activity3);
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

	@Override
	public void update(Activity activity) {
		long activityId = activity.getId();
		activitiesMap.put(activityId, activity);
	}

	@Override
	public void delete(long activityId) {
		activitiesMap.remove(activityId);
	}

	@Override
	public List<Activity> findByDescriptions(List<String> descriptions) {
		Set<String> setOfDescriptions = new HashSet<>(descriptions);
		List<Activity> activities = activitiesMap.values()
				.stream()
				.filter(activity -> setOfDescriptions.contains(activity.getDescription()))
				.collect(Collectors.toList());
		return activities;
	}

	@Override
	public List<Activity> findByDescriptionsAndDuration(List<String> descriptions, int durationFrom, int durationTo) {
		Set<String> setOfDescriptions = new HashSet<>(descriptions);
		List<Activity> activities = activitiesMap.values()
				.stream()
				.filter(activity -> activity.getDuration() >= durationFrom)
				.filter(activity -> activity.getDuration() <= durationTo)
				.filter(activity -> setOfDescriptions.contains(activity.getDescription()))
				.collect(Collectors.toList());
		return activities;
	}

	@Override
	public List<Activity> findByConstraints(ActivitySearch activitySearch) {
		Set<String> setOfDescriptions = new HashSet<>(activitySearch.getDescriptions());
		Stream<Activity> activityStream = activitiesMap.values().stream();
		switch (activitySearch.getActivitySearch()) {
			case SEARCH_BY_DESCRIPTIONS:
				activityStream = activityStream.filter(activity -> setOfDescriptions.contains(activity.getDescription()));
				break;
			case SEARCH_BY_DURATION:
				activityStream = activityStream.filter(activity -> activity.getDuration() >= activitySearch.getDurationFrom())
						.filter(activity -> activity.getDuration() <= activitySearch.getDurationTo());
				break;
			case SEARCH_BY_ALL_PARAMS:
				activityStream = activityStream.filter(activity -> setOfDescriptions.contains(activity.getDescription()))
						.filter(activity -> activity.getDuration() >= activitySearch.getDurationFrom())
						.filter(activity -> activity.getDuration() <= activitySearch.getDurationTo());
			default:
				break;

		}
		List<Activity> activities =	activityStream.collect(Collectors.toList());
		return activities;
	}
}
