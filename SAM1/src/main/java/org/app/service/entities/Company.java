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
	String name;
	String address;
	String description;
	@OneToMany(mappedBy = "company", cascade = ALL, orphanRemoval = true)
	List<JobOffer> listJobOffer;
	@OneToMany(mappedBy = "company", cascade = ALL, orphanRemoval = true)
	List<Messages> listMessages;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Company(String name, String address, String description, List<JobOffer> listJobOffer,
			List<Messages> listMessages) {
		super();
		this.name = name;
		this.address = address;
		this.description = description;
		this.listJobOffer = listJobOffer;
		this.listMessages = listMessages;
	}
	public Company(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public Company(Integer id, String name) {
		super(id);
		this.name = name;
	}
	
	
}
