package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;

@Entity
public class Program implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Program", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Program")
	@GeneratedValue(generator = "SEQ_Program", strategy = SEQUENCE)
	Long idProgram;
	String name;
	String description;
	@ManyToOne
	StudyField studyField;
	@OneToMany(orphanRemoval = true, cascade = ALL, mappedBy = "program")
	List<Degree> listDegrees;
}
