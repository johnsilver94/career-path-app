package org.app.service.entities;

import java.util.List;

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
	List<String> listSkills;    
	List<String> listForeignLanguages;
	@OneToOne(mappedBy = "cv")
	ProfessionalExperience professionalExperience;
}
