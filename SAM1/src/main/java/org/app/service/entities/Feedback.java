package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Feedback implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_Feedback", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Feedback")
	@GeneratedValue(generator = "SEQ_Feedback", strategy = SEQUENCE)
	Long idFeedback;
	@ManyToOne
	Users user;
	@OneToOne
	Users oboutUser;
	String description;
	public Long getIdFeedback() {
		return idFeedback;
	}
	public void setIdFeedback(Long idFeedback) {
		this.idFeedback = idFeedback;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Users getOboutUser() {
		return oboutUser;
	}
	public void setOboutUser(Users oboutUser) {
		this.oboutUser = oboutUser;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(Long idFeedback, Users user, Users oboutUser, String description) {
		super();
		this.idFeedback = idFeedback;
		this.user = user;
		this.oboutUser = oboutUser;
		this.description = description;
	}
	
}
