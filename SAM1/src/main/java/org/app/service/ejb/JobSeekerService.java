package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.CV;
import org.app.service.entities.JobOffer;
import org.app.service.entities.JobSeeker;

@Remote
public interface JobSeekerService extends EntityRepository<JobSeeker>{

	CV addCvToJobSeeker(JobSeeker curentSeeker, CV cvToAdd) throws Exception;
	
	JobSeeker addJobSeeker(JobSeeker jobSeekerToAdd) throws Exception;

	// DELETE
	Collection<JobSeeker> removeJobSeeker(JobSeeker jobSeekerToDelete) throws Exception;

	// READ
	JobSeeker getJobSeekerById(Long jobSeekerId) throws Exception;
	
	Collection<JobSeeker> getJobSeekers() throws Exception;

	Collection<JobSeeker> addIntoJobSeekers(JobSeeker jobSeekerToAdd) throws Exception;
	

}
