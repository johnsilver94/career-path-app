package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.ManyToOne;
import javax.persistence.Inheritance;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import javax.persistence.DiscriminatorColumn;
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public abstract class ProfExp implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_PE", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_PE")
	@GeneratedValue(generator = "SEQ_PE", strategy = SEQUENCE)
	Long idProfExp;
	String description;
	Date	 fromDate;
	Date     toDate;
	@ManyToOne
	CV       cv;
}
