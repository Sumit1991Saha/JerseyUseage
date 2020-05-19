package com.saha.xml.repository;

import com.saha.xml.model.Activity;

import java.util.List;

public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity findActivity(String activityId);

}