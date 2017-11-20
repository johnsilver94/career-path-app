package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Departament implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Departament", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Departament", schema = "as", catalog = "ass")
	@GeneratedValue(generator = "SEQ_Departament", strategy = SEQUENCE)
	Integer idDepartament;
	String name;
	String description;
}
