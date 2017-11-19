package org.app.service.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
@Entity
public class JobOffer implements Serializable{
	@Id @GeneratedValue
	Integer idOffer;
	@ManyToOne
	Position position;
	@ManyToOne
	Company  company;
	String   careerLevel;
	String educationRequirements;
	String responsabilities;
	String workHours;
	String workEnvironment;
	String status;
	@ElementCollection(targetClass = String.class)
	List<String> listRequiredSkills;  
	@ManyToMany
	List<JobSeeker> listJobSeeker;
}
