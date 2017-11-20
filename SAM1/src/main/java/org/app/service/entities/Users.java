package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.validation.constraints.NotNull;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.io.Serializable;
import javax.persistence.SequenceGenerator;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class Users implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_USER", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_USER")
	@GeneratedValue(generator = "SEQ_USER", strategy = SEQUENCE)
	Integer idUser;
	String userName;
	String passWord;
	String email;
	String phoneNumber;
	
	public Integer getId() {
		return idUser;
	}
	public void setId(Integer idUser) {
		this.idUser = idUser;
	}
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
	public Users(Integer idUser) {
		super();
		this.idUser = idUser;
	}
	public Users(Integer idUser, String userName, String passWord) {
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
