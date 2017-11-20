package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.app.service.enums.CareerLevel;
import org.app.service.enums.Degree;
import org.app.service.enums.JOStatus;
import org.app.service.enums.WorkEnvironment;
import org.app.service.enums.WorkHours;

import javax.persistence.ManyToMany;
@Entity
public class JobOffer implements Serializable{
	@Id 
	@SequenceGenerator(name = "SEQ_JobOffer", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_JobOffer")
	@GeneratedValue(generator = "SEQ_JobOffer", strategy = SEQUENCE)
	Integer idOffer;
	@ManyToOne
	Position position;
	@ManyToOne
	Company  company;
	CareerLevel   careerLevel;
	Degree educationRequirements;
	String responsabilities;
	WorkHours workHours;
	WorkEnvironment workEnvironment;
	JOStatus status;
	@ElementCollection(targetClass = String.class)
	List<String> listRequiredSkills;  
	@ManyToMany
	List<JobSeeker> listJobSeeker;
	public JobOffer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobOffer(Integer idOffer, String responsabilities) {
		super();
		this.idOffer = idOffer;
		this.responsabilities = responsabilities;
	}
	
}
