package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.DiscriminatorColumn;
import static javax.persistence.DiscriminatorType.STRING;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@Table(name = "Users")
@DiscriminatorColumn(name = "UserType", discriminatorType = STRING)
public abstract class Users {
	@Id
	Integer id;
	String userName;
	String passWord;

}
