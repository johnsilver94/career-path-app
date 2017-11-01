package org.app.service.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
@Entity
public class JobOffer {
	@Id
	Integer id;
	@ManyToOne
	Position position;
	@ManyToOne
	Company  company;
	String   jobLevel;
	String   responsabilities;
	@ManyToMany
	List<JobSeeker> listJobSeeker;
}
