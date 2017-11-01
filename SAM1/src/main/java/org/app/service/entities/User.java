package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class User {
	@Id
	String userName;
	String passWord;

}
