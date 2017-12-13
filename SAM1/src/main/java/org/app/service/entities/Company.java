package org.app.service.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Basic;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.ManyToMany;

@Entity
@XmlRootElement(name="company")
@XmlAccessorType(XmlAccessType.NONE)
//@DiscriminatorValue("Company")
public class Company extends Users {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String companyName;
	String companyDescription;
	@OneToMany(mappedBy = "company", cascade = ALL, orphanRemoval = true, fetch = EAGER)
	List<JobOffer> listJobOffer;
	@OneToMany(orphanRemoval = false, cascade = ALL, mappedBy = "company")
	List<Employee> listEmployees;
	//maybe inverse
	@ManyToMany(mappedBy = "projectCompanies")
	List<Project> listProjects;

	@XmlElementWrapper(name = "jobOffers") @XmlElement(name = "jobOffer")
	public List<JobOffer> getListJobOffer() {
		return listJobOffer;
	}
	public void setListJobOffer(List<JobOffer> listJobOffer) {
		this.listJobOffer = listJobOffer;
	}
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	@XmlElement
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@XmlElement
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	//@XmlElementWrapper(name = "employees") @XmlElement(name = "employee")
	public List<Employee> getListEmployees() {
		return listEmployees;
	}
	public void setListEmployees(List<Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}
	public List<Project> getListProjects() {
		return listProjects;
	}
	public void setListProjects(List<Project> listProjects) {
		this.listProjects = listProjects;
	}
	public Company(String companyName,  String companyDescription, List<JobOffer> listJobOffer,
			List<Message> listMessages) {
		super();
		this.companyName = companyName;
		this.companyDescription = companyDescription;
		this.listJobOffer = listJobOffer;
		this.listMessages = listMessages;
	}
	public Company(Long id, String companyName) {
		super(id);
		this.companyName = companyName;
	}
	
	public static String BASE_URL = "http://localhost:8080/SAM/data/companies";
	
	@XmlElement(name="link") 
	public AtomLink getLink() throws Exception
	{
		String restUrl = BASE_URL +"/" + this.getIdUser();
		return new AtomLink(restUrl,"get-Company");
	}
	public void setLink(AtomLink link) {}
}
