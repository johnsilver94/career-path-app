package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.app.service.enums.Degree;

@Entity
public class Education  implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_Education", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Education")
	@GeneratedValue(generator = "SEQ_Education", strategy = SEQUENCE)
	Integer idEducation;
	School school;
	Programs program;
	Degree degree;
	Date   fromYear;
	Date   toYear;
	@ManyToOne
	CV cv;
}
