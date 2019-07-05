package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.ALL;

@Entity
public class School implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7980784101571043564L;
	@Id
	@SequenceGenerator(name = "SEQ_School", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_School")
	@GeneratedValue(generator = "SEQ_School", strategy = SEQUENCE)
	Long idSchool;
	String name;
	@OneToOne
	Location location;
	String acceptanceRate;
	String phoneNumber;
	String description;
	String parentOrganization;
	String webSite;
	@OneToMany(orphanRemoval = true, cascade = ALL, mappedBy = "school")
	List<StudyField> listStudyFields;
}
