package com.saha.repository;

import com.saha.model.Activity;
import com.saha.model.ActivitySearch;

import java.util.List;

public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity findActivity(long activityId);

	void create(Activity activity);

	void update(Activity activity);

	void delete(long id);

	List<Activity> findByDescriptions(List<String> descriptions);

	List<Activity> findByDescriptionsAndDuration(List<String> descriptions, int durationFrom, int durationTo);

	List<Activity> findByConstraints(ActivitySearch activitySearch);
}
