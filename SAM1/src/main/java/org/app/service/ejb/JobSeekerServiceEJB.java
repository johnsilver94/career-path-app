package org.app.service.ejb;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.CV;
import org.app.service.entities.JobSeeker;
import org.app.service.entities.Message;

@Stateless @LocalBean
public class JobSeekerServiceEJB extends EntityRepositoryBase<JobSeeker> implements JobSeekerService {
	private static Logger logger = Logger.getLogger(JobSeekerServiceEJB.class.getName());
	
	//@EJB
	private EntityRepository<JobSeeker> seekerRepository;
	private EntityRepository<CV> cvRepository;
	
    @PostConstruct
	public void init(){
    	cvRepository	=	new EntityRepositoryBase<CV>(this.em,CV.class);
		logger.info("Initialized :");		
	}

	@Override
	public CV addCvToJobSeeker(JobSeeker curentSeeker, CV cvToAdd) throws Exception {
		cvToAdd.setJobSeeker(curentSeeker);
		return cvRepository.add(cvToAdd);
	}	
}
