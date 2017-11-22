package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Degree implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Degree", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Degree")
	@GeneratedValue(generator = "SEQ_Degree", strategy = SEQUENCE)
	Long idDegree;
	@ManyToOne
	Program program;
	DegreeLevel degreelevel;
	String name;
}
