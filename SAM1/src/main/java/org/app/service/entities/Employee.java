package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.ManyToOne;

@Entity
public class Employee implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Employee", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Employee")
	@GeneratedValue(generator = "SEQ_Employee", strategy = SEQUENCE)
	Long idEmployee;
	@ManyToOne
	JobSeeker employee;
	@OneToOne
	Position position;
	@ManyToOne
	Company company;
}
