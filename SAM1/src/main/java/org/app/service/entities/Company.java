package org.app.service.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("Company")
public class Company extends Users {
	String name;
	String address;
	String description;
	@OneToMany(mappedBy = "company", cascade = ALL, fetch = EAGER, orphanRemoval = true)
	List<JobOffer> listJobOffer;
	@OneToMany(mappedBy = "company", cascade = ALL, orphanRemoval = true, fetch = EAGER)
	List<Messages> listMessages;
}
