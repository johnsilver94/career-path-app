package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.ALL;

@Entity
public class School implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_School", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_School")
	@GeneratedValue(generator = "SEQ_School", strategy = SEQUENCE)
	Integer idSchool;
	String name;
	String region;
	String city;
	String address;
	String acceptanceRate;
	String phoneNumber;
	String description;
	String studyField;
	String parentOrganization;
	String webSite;
	List<Programs> listPrograms;
}
