package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class StudyField implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_StudyField", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_StudyField")
	@GeneratedValue(generator = "SEQ_StudyField", strategy = SEQUENCE)
	Long idStudyField;
	String name;
	String description;
	@ManyToOne
	School school;
	@OneToMany(orphanRemoval = true, cascade = ALL, mappedBy = "studyField")
	List<Program> listPrograms;
}
