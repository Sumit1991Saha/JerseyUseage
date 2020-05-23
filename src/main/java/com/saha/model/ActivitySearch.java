package com.saha.model;

import lombok.Data;

import java.util.List;

@Data
public class ActivitySearch {
    int durationFrom;
    int durationTo;
    List<String> descriptions;
    ActivitySearchType activitySearch;
}
