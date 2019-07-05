package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import static javax.persistence.CascadeType.ALL;

@Entity
public class PatchTest implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_PatchTest", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_PatchTest")
	@GeneratedValue(generator = "SEQ_PatchTest", strategy = SEQUENCE)
	Long idPatchTest;
	String name;
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "patchTest")
	List<Question> listQuestions;
}
