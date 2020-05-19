package com.saha.xml.model;

import lombok.Data;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class Activity {
	private String id;
	private String description;
	private int duration;
	private User user;
}
