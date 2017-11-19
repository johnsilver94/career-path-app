package org.app.service.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;

@Entity
public class CV implements Serializable {
	@Id @GeneratedValue
	Integer id;
	String careerLevel;
	String joinInterestArea;
	String objectives;
	@OneToOne
	JobSeeker	jobSeeker;
	@ElementCollection(targetClass = String.class)
	List<String> listSkills;  
	@ElementCollection(targetClass = String.class)
	List<String> listForeignLanguages;
	@OneToMany(mappedBy = "cv", cascade = ALL, orphanRemoval = true)
	List<ProfessionalExperience> listProfessionalExperience;
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "cv")
	List<Education>	listEducation;
}
