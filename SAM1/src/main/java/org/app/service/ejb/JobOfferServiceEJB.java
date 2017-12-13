package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.JobOffer;

@Path("jobOffers")
@Stateless @LocalBean
public class JobOfferServiceEJB implements JobOfferService {
	private static Logger logger = Logger.getLogger(JobOfferServiceEJB.class.getName());

	@PersistenceContext(unitName="SAM1")
 	private EntityManager em;
	
	@PUT @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public JobOffer addJobOffer(JobOffer jobOfferToAdd) {
		if(jobOfferToAdd.getIdOffer() == null)
		{
			em.persist(jobOfferToAdd);
			em.flush();
			em.refresh(jobOfferToAdd);
		}
		else 
		{
			em.merge(jobOfferToAdd);
		}
		return jobOfferToAdd;
	}
	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Collection<JobOffer> removeJobOffer(JobOffer jobOfferToDelete) {
		jobOfferToDelete = em.merge(jobOfferToDelete);
		em.remove(jobOfferToDelete);
		em.flush();
		return this.getJobOffers();
	}

	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public JobOffer getJobOfferById(@PathParam("id")Long jobOfferId) {
		return em.createQuery("SELECT jo FROM JobOffer jo WHERE jo.idOffer = :jobOfferId",JobOffer.class)
				.setParameter("jobOfferId", jobOfferId)
				.getSingleResult();
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<JobOffer> getJobOffers() {
		List<JobOffer> jobOffers = em.createQuery("SELECT jo FROM JobOffer jo",JobOffer.class)
				.getResultList();
		return jobOffers;
	}

}
