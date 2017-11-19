package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class Employee implements Serializable {
	@Id @GeneratedValue
	Integer idEmployee;
	@OneToOne
	JobSeeker employee;
	@OneToOne
	Position position;
	@ManyToOne
	Company company;
}
