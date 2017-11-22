package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Language implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Language", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Language")
	@GeneratedValue(generator = "SEQ_Language", strategy = SEQUENCE)
	Long idLanguage;
	String Name;
	LanguageLevel languageLevel;
	@ManyToOne
	CV cv;
}
