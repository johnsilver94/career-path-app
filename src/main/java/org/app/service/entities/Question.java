package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import static javax.persistence.CascadeType.ALL;

@Entity
public class Question implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_Question", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Question")
	@GeneratedValue(generator = "SEQ_Question", strategy = SEQUENCE)
	Long idQuestion;
	String Description;
	@ManyToOne
	PatchTest patchTest;
	@OneToMany(orphanRemoval = true, cascade = ALL, mappedBy = "question")
	List<ResOption> listResOptions; 
}
