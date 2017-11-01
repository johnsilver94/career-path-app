package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Messages {
	@Id
	Integer id;
	String  title;
	String  text;
	@ManyToOne
	JobSeeker jobSeeker;
	@ManyToOne
	Company   company;
}
