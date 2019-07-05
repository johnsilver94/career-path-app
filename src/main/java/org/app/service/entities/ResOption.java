package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ResOption implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_ResOption", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_ResOption")
	@GeneratedValue(generator = "SEQ_ResOption", strategy = SEQUENCE)
	Long idResOption;
	@ManyToOne
	Question question;
	@Enumerated(EnumType.STRING)
	QuestionRes questionRes;
	@ManyToOne
	Skill skill;
}
