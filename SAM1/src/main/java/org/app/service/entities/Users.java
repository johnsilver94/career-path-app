package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.io.Serializable;
import java.util.List;

import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlElement;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.FetchType.LAZY;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2016137212391174933L;
	@Id
	@SequenceGenerator(name = "SEQ_USER", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_USER")
	@GeneratedValue(generator = "SEQ_USER", strategy = SEQUENCE)
	Long idUser;
	String userName;
	String passWord;
	String email;
	String phoneNumber;
	@OneToOne(cascade = ALL)
	Location location;
	@OneToMany(mappedBy = "fromUser", cascade = ALL, orphanRemoval = false, fetch = EAGER)
	List<Message> listMessages;/*
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "user", fetch = LAZY)
	List<Feedback> listFeedback;*/
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Users(Long idUser) {
		super();
		this.idUser = idUser;
	}
	
	public List<Message> getListMessages() {
		return listMessages;
	}
	@XmlElement
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public void setListMessages(List<Message> listMessages) {
		this.listMessages = listMessages;
	}/*
	public List<Feedback> getListFeedback() {
		return listFeedback;
	}
	public void setListFeedback(List<Feedback> listFeedback) {
		this.listFeedback = listFeedback;
	}*/
	public Users(Long idUser, String userName, String passWord) {
		super();
		this.idUser = idUser;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
