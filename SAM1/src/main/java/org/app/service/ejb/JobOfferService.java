package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.JobOffer;

@Remote
public interface JobOfferService {
	
	JobOffer addJobOffer(JobOffer jobOfferToAdd) throws Exception;

	// DELETE
	Collection<JobOffer> removeJobOffer(JobOffer jobOfferToDelete) throws Exception;

	// READ
	JobOffer getJobOfferById(Long jobOfferId) throws Exception;
	
	Collection<JobOffer> getJobOffers() throws Exception;

	Collection<JobOffer> addIntoJobOffers(JobOffer jobOfferToAdd) throws Exception;
	

}
