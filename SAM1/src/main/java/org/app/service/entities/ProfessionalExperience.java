package org.app.service.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
@Entity
public class ProfessionalExperience implements Serializable {
	@Id@GeneratedValue
	Integer idExperience;
	String projectName;
	String description;
	@OneToOne
	Position position;
	Date	 fromDate;
	Date     toDate;
	@ManyToOne
	CV       cv;
}
