package org.app.service.entities;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CV {
	@Id
	Integer id;
	String jobLevel;
	String joinInterestArea;
	
	@OneToOne
	JobSeeker	jobSeeker;
	
	@ElementCollection(targetClass = String.class)
	List<String> listSkills;  
	
	@ElementCollection(targetClass = String.class)
	List<String> listForeignLanguages;
	
	@OneToOne(mappedBy = "cv")
	ProfessionalExperience professionalExperience;
}
