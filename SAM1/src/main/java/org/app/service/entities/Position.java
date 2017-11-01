package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Position {
	@Id
	Integer id;
	String  name;
	String  description;
	@OneToOne
	JobSeeker jobSeeker;
	@OneToOne(mappedBy = "position")
	ProfessionalExperience professionalExperience;
}
