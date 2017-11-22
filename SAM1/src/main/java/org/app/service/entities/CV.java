package org.app.service.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Enumerated;
import static javax.persistence.EnumType.STRING;

@Entity
public class CV implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_CV", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_CV")
	@GeneratedValue(generator = "SEQ_CV", strategy = SEQUENCE)
	Long idCV;
	@Enumerated(EnumType.STRING)
	CareerLevel careerLevel;
	String objectives;
	@OneToOne
	JobSeeker	jobSeeker;
	@OneToMany
	List<Skill> listSkills;  
	@OneToMany(orphanRemoval = true, cascade = ALL, mappedBy = "cv")
	List<Language> listLanguages;
	@OneToMany(mappedBy = "cv", cascade = ALL, orphanRemoval = true)
	List<ProfExp> listProfExp;
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "cv")
	List<Education>	listEducation;
}
