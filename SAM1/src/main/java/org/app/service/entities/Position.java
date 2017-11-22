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
public class Position implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Position", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Position")
	@GeneratedValue(generator = "SEQ_Position", strategy = SEQUENCE)
	Long idPosition;
	String  name;
	String  description;
	@ManyToOne
	Departament departament;
}
