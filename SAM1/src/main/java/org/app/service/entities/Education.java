package org.app.service.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Education  implements Serializable{
	@Id @GeneratedValue
	Integer idEducation;
	String school;
	String degree;
	String studyField;
	String grade;
	Date   fromYear;
	Date   toYear;
	@ManyToOne
	CV cv;
}
