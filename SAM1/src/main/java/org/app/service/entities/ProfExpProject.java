package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("ProfExpProject")
public class ProfExpProject extends ProfExp{
	@ManyToOne
	Project project;
	String role;
}
