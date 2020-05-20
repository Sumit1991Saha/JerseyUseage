package com.saha.model;

import lombok.Data;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
public class User {
	private String name;
	private long id;
}
