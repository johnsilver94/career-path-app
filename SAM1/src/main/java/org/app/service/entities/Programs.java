package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.app.service.enums.Degree;

@Entity
public class Programs implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Programs", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Programs")
	@GeneratedValue(generator = "SEQ_Programs", strategy = SEQUENCE)
	Integer idPrograms;
	String name;
	String description;
	List<Degree> degrees;
}
