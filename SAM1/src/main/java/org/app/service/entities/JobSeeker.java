package org.app.service.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
@Entity
public class JobSeeker extends User{
	Integer age;
	String  name;
	String 	surname;
	@OneToOne(cascade = ALL, mappedBy = "jobSeeker")
	Position position;
	@OneToMany(mappedBy = "jobSeeker", cascade = ALL, orphanRemoval = true, fetch = EAGER)
	List<Messages> listMessages;
	@ManyToMany(fetch = EAGER, mappedBy = "listJobSeeker", cascade = ALL)
	List<JobOffer> listJobOfferAplication;
}
