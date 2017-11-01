package org.app.service.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class ProfessionalExperience {
	@Id
	Integer id;
	String projectName;
	@OneToOne
	Position position;
	Date	 fromDate;
	Date     toDate;
	@OneToOne
	CV       cv;
}
