package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;

@Entity
public class Departament implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Departament", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Departament")
	@GeneratedValue(generator = "SEQ_Departament", strategy = SEQUENCE)
	Long idDepartament;
	String name;
	String description;	
	Double fromSalary;
	Double toSalary;
	@OneToMany(orphanRemoval = true, cascade = ALL, mappedBy = "departament")
	List<Position> listPositions;
	@OneToMany
	List<Degree> listDegreeRequired;
}
