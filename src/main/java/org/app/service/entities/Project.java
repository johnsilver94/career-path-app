package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Project  implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_Project", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Project")
	@GeneratedValue(generator = "SEQ_Project", strategy = SEQUENCE)
	Long idProject;
	String name;
	@ManyToMany
	List<Company> projectCompanies;
	String description;
}
