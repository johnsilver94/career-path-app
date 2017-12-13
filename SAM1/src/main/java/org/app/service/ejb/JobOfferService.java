package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.JobOffer;

@Remote
public interface JobOfferService {
	
	JobOffer addJobOffer(JobOffer jobOfferToAdd);

	// DELETE
	Collection<JobOffer> removeJobOffer(JobOffer jobOfferToDelete);

	// READ
	JobOffer getJobOfferById(Long jobOfferId);
	
	Collection<JobOffer> getJobOffers();
	

}
