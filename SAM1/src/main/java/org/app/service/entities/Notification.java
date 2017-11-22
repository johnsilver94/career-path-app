package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Notification extends Message{
	@ManyToOne
	JobOffer jobOffer;
	String notificationType;
}
