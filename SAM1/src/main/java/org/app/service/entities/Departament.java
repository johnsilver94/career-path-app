package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Departament implements Serializable {
	@Id @GeneratedValue
	Integer idDepartament;
	String name;
	String description;
}
