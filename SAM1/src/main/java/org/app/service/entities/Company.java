package org.app.service.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Basic;
import static javax.persistence.FetchType.LAZY;

@Entity
//@DiscriminatorValue("Company")
public class Company extends Users {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String companyName;
	String companyRegion;
	String companyCounty;
	String companyAddress;
	String companyDescription;
	@OneToMany(mappedBy = "company", cascade = ALL, orphanRemoval = true)
	List<JobOffer> listJobOffer;
	@OneToMany(mappedBy = "company", cascade = ALL, orphanRemoval = true)
	List<Messages> listMessages;
	@OneToMany(orphanRemoval = true, cascade = ALL, mappedBy = "company")
	List<Employee> listEmployees;
	public String getName() {
		return companyName;
	}
	public void setName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return companyAddress;
	}
	public void setAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getDescription() {
		return companyDescription;
	}
	public void setDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
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
	public List<Messages> getListMessages() {
		return listMessages;
	}
	public void setListMessages(List<Messages> listMessages) {
		this.listMessages = listMessages;
	}
	public Company(String companyName, String companyAddress, String companyDescription, List<JobOffer> listJobOffer,
			List<Messages> listMessages) {
		super();
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyDescription = companyDescription;
		this.listJobOffer = listJobOffer;
		this.listMessages = listMessages;
	}
	public Company(Integer id, String companyName) {
		super(id);
		this.companyName = companyName;
	}
}
