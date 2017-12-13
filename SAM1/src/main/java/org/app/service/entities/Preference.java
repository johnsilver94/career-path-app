package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Enumerated;
import static javax.persistence.EnumType.STRING;

@Entity
public class Preference implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Preference", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Preference")
	@GeneratedValue(generator = "SEQ_Preference", strategy = SEQUENCE)
	Long idPreference;
	@OneToOne
	JobSeeker jobSeeker;
	@Enumerated(EnumType.STRING)
	WorkEnvironment workEnvironment;
	@Enumerated(EnumType.STRING)
	WorkHours workHours;
	@OneToOne
	Company company;
	@OneToOne
	Departament departament;
	@OneToOne
	Position position;
	String country;
	String region;
	String county;
	String city;
	
	
}
