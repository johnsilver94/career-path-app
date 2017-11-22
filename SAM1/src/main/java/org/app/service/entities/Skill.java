package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Skill implements Serializable {
	@Id 
	@SequenceGenerator(name = "SEQ_Skill", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Skill")
	@GeneratedValue(generator = "SEQ_Skill", strategy = SEQUENCE)
	Integer idSkill;
	String Description;
}
