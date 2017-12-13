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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static javax.persistence.CascadeType.ALL;

@XmlRootElement(name="jobOffer") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class JobOffer implements Serializable{
	@Id 
	@SequenceGenerator(name = "SEQ_JobOffer", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_JobOffer")
	@GeneratedValue(generator = "SEQ_JobOffer", strategy = SEQUENCE)
	Long idOffer;
	@OneToOne
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
	// Maybe list must mapped in JobSeeker class
	@ManyToMany(cascade = ALL, mappedBy = "listJobOfferAplication")
	List<JobSeeker> listJobSeeker;
	
	@XmlElement
	public Long getIdOffer() {
		return idOffer;
	}
	public void setIdOffer(Long idOffer) {
		this.idOffer = idOffer;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	@XmlElement
	public CareerLevel getCareerLevel() {
		return careerLevel;
	}
	public void setCareerLevel(CareerLevel careerLevel) {
		this.careerLevel = careerLevel;
	}
	@XmlElement
	public DegreeLevel getEducationRequirements() {
		return educationRequirements;
	}
	public void setEducationRequirements(DegreeLevel educationRequirements) {
		this.educationRequirements = educationRequirements;
	}
	@XmlElement
	public String getResponsabilities() {
		return responsabilities;
	}
	public void setResponsabilities(String responsabilities) {
		this.responsabilities = responsabilities;
	}
	public WorkHours getWorkHours() {
		return workHours;
	}
	public void setWorkHours(WorkHours workHours) {
		this.workHours = workHours;
	}
	public WorkEnvironment getWorkEnvironment() {
		return workEnvironment;
	}
	public void setWorkEnvironment(WorkEnvironment workEnvironment) {
		this.workEnvironment = workEnvironment;
	}
	public JOStatus getStatus() {
		return status;
	}
	public void setStatus(JOStatus status) {
		this.status = status;
	}
	public List<Skill> getListRequiredSkills() {
		return listRequiredSkills;
	}
	public void setListRequiredSkills(List<Skill> listRequiredSkills) {
		this.listRequiredSkills = listRequiredSkills;
	}
	public List<JobSeeker> getListJobSeeker() {
		return listJobSeeker;
	}
	public void setListJobSeeker(List<JobSeeker> listJobSeeker) {
		this.listJobSeeker = listJobSeeker;
	}
	public JobOffer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobOffer(Long idOffer, String responsabilities) {
		super();
		this.idOffer = idOffer;
		this.responsabilities = responsabilities;
	}
	
	public static String BASE_URL = Company.BASE_URL;
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL 
				+ ((this.getCompany() != null) ? this.getCompany().getIdUser() : "")
				+ "/jobOffers/" 
				+ this.getIdOffer();
        return new AtomLink(restUrl, "get-jobOffers");
    }	
	public void setLink(AtomLink link){}

	
}
