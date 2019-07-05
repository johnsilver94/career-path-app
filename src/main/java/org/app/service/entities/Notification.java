package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notification extends Message{
	@OneToOne
	JobOffer jobOffer;
	String notificationType;
}
