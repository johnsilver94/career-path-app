package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
@Entity
public class JobOffer implements Serializable{
	@Id 
	@SequenceGenerator(name = "SEQ_JobOffer", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_JobOffer")
	@GeneratedValue(generator = "SEQ_JobOffer", strategy = SEQUENCE)
	Long idOffer;
	@ManyToOne
	Position position;
	@ManyToOne
	Company  company;
	@Enumerated(EnumType.STRING)
	CareerLevel   careerLevel;
	@Enumerated(EnumType.STRING)
	DegreeLevel educationRequirements;
	String responsabilities;
	@Enumerated(EnumType.STRING)
	WorkHours workHours;
	@Enumerated(EnumType.STRING)
	WorkEnvironment workEnvironment;
	@Enumerated(EnumType.STRING)
	JOStatus status;
	@OneToMany
	List<Skill> listRequiredSkills;  
	@ManyToMany(cascade = ALL, mappedBy = "listJobOfferAplication")
	List<JobSeeker> listJobSeeker;
	public JobOffer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobOffer(Long idOffer, String responsabilities) {
		super();
		this.idOffer = idOffer;
		this.responsabilities = responsabilities;
	}
	
}
