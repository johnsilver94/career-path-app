package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.validation.constraints.NotNull;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.io.Serializable;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class Users implements Serializable{
	@Id @GeneratedValue
	Integer id;
	String userName;
	String passWord;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Users(Integer id) {
		super();
		this.id = id;
	}
	public Users(Integer id, String userName, String passWord) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
