package org.app.service.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.DiscriminatorValue;
@Entity
//@DiscriminatorValue("JobSeeker")
public class JobSeeker extends Users{
	Integer age;
	String  name;
	String 	surname;
	@OneToOne(cascade = ALL, mappedBy = "jobSeeker")
	Position position;
	@OneToMany(mappedBy = "jobSeeker", cascade = ALL, orphanRemoval = true)
	List<Messages> listMessages;
	@ManyToMany(mappedBy = "listJobSeeker", cascade = ALL)
	List<JobOffer> listJobOfferAplication;
}
