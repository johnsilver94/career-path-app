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
	public Long getIdCV() {
		return idCV;
	}
	public void setIdCV(Long idCV) {
		this.idCV = idCV;
	}
	public CareerLevel getCareerLevel() {
		return careerLevel;
	}
	public void setCareerLevel(CareerLevel careerLevel) {
		this.careerLevel = careerLevel;
	}
	public String getObjectives() {
		return objectives;
	}
	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	public List<Skill> getListSkills() {
		return listSkills;
	}
	public void setListSkills(List<Skill> listSkills) {
		this.listSkills = listSkills;
	}
	public List<Language> getListLanguages() {
		return listLanguages;
	}
	public void setListLanguages(List<Language> listLanguages) {
		this.listLanguages = listLanguages;
	}
	public List<ProfExp> getListProfExp() {
		return listProfExp;
	}
	public void setListProfExp(List<ProfExp> listProfExp) {
		this.listProfExp = listProfExp;
	}
	public List<Education> getListEducation() {
		return listEducation;
	}
	public void setListEducation(List<Education> listEducation) {
		this.listEducation = listEducation;
	}
	public CV() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CV(Long idCV, CareerLevel careerLevel, String objectives, JobSeeker jobSeeker, List<Skill> listSkills,
			List<Language> listLanguages, List<ProfExp> listProfExp, List<Education> listEducation) {
		super();
		this.idCV = idCV;
		this.careerLevel = careerLevel;
		this.objectives = objectives;
		this.jobSeeker = jobSeeker;
		this.listSkills = listSkills;
		this.listLanguages = listLanguages;
		this.listProfExp = listProfExp;
		this.listEducation = listEducation;
	}
	
	
}
