package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Messages implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_Messages", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Messages")
	@GeneratedValue(generator = "SEQ_Messages", strategy = SEQUENCE)
	Integer idMessage;
	String  title;
	String  text;
	@ManyToOne
	JobSeeker jobSeeker;
	@ManyToOne
	Company   company;
	
	public Integer getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Integer idMessage) {
		this.idMessage = idMessage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Messages(Integer idMessage, String text) {
		super();
		this.idMessage = idMessage;
		this.text = text;
	}
	public Messages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Messages(Integer idMessage, String title, String text, JobSeeker jobSeeker, Company company) {
		super();
		this.idMessage = idMessage;
		this.title = title;
		this.text = text;
		this.jobSeeker = jobSeeker;
		this.company = company;
	}
	
	
}
