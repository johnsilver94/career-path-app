package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Position implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Position", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Position")
	@GeneratedValue(generator = "SEQ_Position", strategy = SEQUENCE)
	Long idPosition;
	String  name;
	String  description;
	Double fromSalary;
	Double toSalary;
	@ManyToOne
	Departament departament;
	@OneToMany
	List<Degree> listDegreeSugerated;
}
