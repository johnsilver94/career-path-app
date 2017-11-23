package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("ProfExpPosition")
public class ProfExpPosition extends ProfExp{
	@OneToOne
	Position position;
	@OneToOne
	Company company;
}
