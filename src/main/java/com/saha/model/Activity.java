package com.saha.model;

import lombok.Data;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class Activity {
	private long id;
	private String description;
	private int duration;
	private User user;
}
