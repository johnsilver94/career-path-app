package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Location implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Location", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Location")
	@GeneratedValue(generator = "SEQ_Location", strategy = SEQUENCE)
	Long IdLocation;
	String country;
	String region;
	String county;
	String city;
	String address;
}
