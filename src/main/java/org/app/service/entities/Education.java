package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Education  implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_Education", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Education")
	@GeneratedValue(generator = "SEQ_Education", strategy = SEQUENCE)
	Long idEducation;
	@OneToOne
	School school;
	@OneToOne
	Program program;
	@Enumerated(EnumType.STRING)
	DegreeLevel degreeLevel;
	Date   fromYear;
	Date   toYear;
	@ManyToOne
	CV cv;
}
