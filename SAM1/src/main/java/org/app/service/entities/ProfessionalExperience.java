package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.ManyToOne;
@Entity
public class ProfessionalExperience implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_PE", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_PE")
	@GeneratedValue(generator = "SEQ_PE", strategy = SEQUENCE)
	Integer idExperience;
	String projectName;
	String description;
	@ManyToOne
	Position position;
	Date	 fromDate;
	Date     toDate;
	@ManyToOne
	CV       cv;
}
