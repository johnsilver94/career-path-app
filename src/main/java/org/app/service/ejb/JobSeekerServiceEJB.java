package org.app.service.ejb;

import java.util.Collection;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.CV;
import org.app.service.entities.JobOffer;
import org.app.service.entities.JobSeeker;
import org.app.service.entities.Message;
@Path("jobSeekers")
@Stateless @LocalBean
public class JobSeekerServiceEJB extends EntityRepositoryBase<JobSeeker> implements JobSeekerService {
	private static Logger logger = Logger.getLogger(JobSeekerServiceEJB.class.getName());
	
	//@EJB
	private EntityRepository<JobSeeker> seekerRepository;
	private EntityRepository<CV> cvRepository;
	
    @PostConstruct
	public void init(){
    	seekerRepository	= new EntityRepositoryBase<JobSeeker>(this.em,JobSeeker.class);
    	cvRepository	=	new EntityRepositoryBase<CV>(this.em,CV.class);
		logger.info("Initialized :");		
	}

	@Override
	public CV addCvToJobSeeker(JobSeeker curentSeeker, CV cvToAdd) throws Exception {
		cvToAdd.setJobSeeker(curentSeeker);
		return cvRepository.add(cvToAdd);
	}
	@PUT @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public JobSeeker addJobSeeker(JobSeeker jobSeekerToAdd) throws Exception {
		if(jobSeekerToAdd.getIdUser() == null || this.getJobSeekerById(jobSeekerToAdd.getIdUser()) == null)
		{
			em.persist(jobSeekerToAdd);
			em.flush();
			em.refresh(jobSeekerToAdd);
		}
		else 
		{
			em.merge(jobSeekerToAdd);
		}
		return jobSeekerToAdd;
	}

	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Collection<JobSeeker> removeJobSeeker(JobSeeker jobSeekerToDelete) throws Exception {
		jobSeekerToDelete = seekerRepository.refresh(jobSeekerToDelete);
		seekerRepository.remove(jobSeekerToDelete);
		return seekerRepository.toCollection();
	}
	
	@DELETE @Path("/{id}") 
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String remove(@PathParam("id")Long id) throws Exception {
		JobSeeker jobSeeker = this.getJobSeekerById((long)id);
		seekerRepository.remove(jobSeeker);
		return "True";
	}

	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public JobSeeker getJobSeekerById(@PathParam("id")Long jobSeekerId) throws Exception {
		return seekerRepository.getById(jobSeekerId);
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<JobSeeker> getJobSeekers() throws Exception {
		return seekerRepository.toCollection();
	}

	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<JobSeeker> addIntoJobSeekers(JobSeeker jobSeekerToAdd) throws Exception {
		this.addJobSeeker(jobSeekerToAdd);
		return this.toCollection();
	}	
}
