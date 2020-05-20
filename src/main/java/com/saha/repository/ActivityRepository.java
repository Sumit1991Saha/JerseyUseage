package com.saha.repository;

import com.saha.model.Activity;

import java.util.List;

public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity findActivity(long activityId);

	void create(Activity activity);

}
