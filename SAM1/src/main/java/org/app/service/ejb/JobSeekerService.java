package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.CV;
import org.app.service.entities.JobSeeker;

@Remote
public interface JobSeekerService extends EntityRepository<JobSeeker>{

	CV addCvToJobSeeker(JobSeeker curentSeeker, CV cvToAdd) throws Exception;
}
