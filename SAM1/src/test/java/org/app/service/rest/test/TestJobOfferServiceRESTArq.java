package org.app.service.rest.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.ejb.CompanyService;
import org.app.service.ejb.CompanyServiceEJB;
import org.app.service.ejb.JobOfferService;
import org.app.service.ejb.JobOfferServiceEJB;
import org.app.service.entities.Company;
import org.app.service.entities.JobOffer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJobOfferServiceRESTArq {
	private static Logger logger = Logger.getLogger(TestJobOfferServiceRESTArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/SAM/data/jobOffers";	

	@EJB@Inject
	private static CompanyService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class)
	                .addPackage(JobOffer.class.getPackage())
	                .addClass(CompanyService.class)
	                .addClass(CompanyServiceEJB.class)
	                .addClass(JobOfferServiceEJB.class)
	                .addClass(JobOfferService.class)
	                .addClass(EntityRepository.class)
	                .addClass(EntityRepositoryBase.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	   }

	@Test
	public void test1_getJobOffers() throws Exception{
		logger.info("DEBUG: Junit TESTING: test1_getJobOffers ...");
		Collection<JobOffer> jobOffers = ClientBuilder.newClient()
				.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<JobOffer>>() {});
		assertTrue("Fail to read Companies!", jobOffers.size() >0);
		jobOffers.stream().forEach(System.out::println);
	}
	@Test
	public void test2_AddJobOffer() throws Exception {
		logger.info("DEBUG: Junit TESTING: test2_AddJobOffer ...");
		Client client = ClientBuilder.newClient();
		Collection<JobOffer> jobOffers;
		Integer nrOfJobOffers = 5;
		JobOffer jobOffer;
		
		Company company =  service.getCompanyById((long)1);
		
		logger.info("Company-------------------- ..."+company.getIdUser());
		for(int i=1;i<=nrOfJobOffers;i++) {
			jobOffer = new JobOffer(null,"Work_Resp_"+nrOfJobOffers);
			jobOffer.setCompany(company);
			jobOffers = client.target(serviceURL)
					.request().accept(MediaType.APPLICATION_XML).header("Content-Type", "application/xml")
					.post(Entity.entity(jobOffer, MediaType.APPLICATION_XML))
					.readEntity(new GenericType<Collection<JobOffer>>() {});
			assertTrue("Fail to read JobOffer!",jobOffers.size() >= 1);
		}
		jobOffers = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<JobOffer>>() {});
		assertTrue("Fail to add JobOffers!",jobOffers.size() >= nrOfJobOffers);
		jobOffers.stream().forEach(System.out::println);
	}
	//@Test
	public void test3_DeleteJobOffer() {
		String resourceURL = serviceURL + "/25";
		logger.info("DEBUG: Junit TESTING: test3_DeleteJobOffer ...");
		Client client = ClientBuilder.newClient();
		String Response;
	    Response = client.target(resourceURL).request().delete().readEntity(String.class);
	    
	    logger.info(">>>>>> DEBUG: REST Response ..." + Response);
	    assertTrue("Is not deleted!", Response.contains("True"));
	}
	//@Test
	public void test5_GetByID() {
		String resourceURL = serviceURL + "/2";
		logger.info("DEBUG: Junit TESTING: t5_GetByID ...");
		
		JobOffer jobOffer = ClientBuilder.newClient().target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(JobOffer.class);
		
		assertNotNull("REST Data Service failed!", jobOffer);
		logger.info(">>>>>> DEBUG: REST Response ..." + jobOffer);
	}
	//@Test
	public void test6_UpdateJobOffer() {
		String resourceURL = serviceURL + "/1";
		logger.info("************* DEBUG: Junit TESTING: test6_UpdateJobOffer ... :" + resourceURL);
		Client client = ClientBuilder.newClient();
		
		JobOffer jobOffer = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_XML)
				.get().readEntity(JobOffer.class);
		
		assertNotNull("REST Data Service failed!", jobOffer );
		logger.info(">>> Initial JobOffer: " + jobOffer );
		
		jobOffer.setResponsabilities("New_RESP");
		jobOffer = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_XML).header("Content-Type", "application/xml")
				.put(Entity.entity(jobOffer, MediaType.APPLICATION_XML))
				.readEntity(JobOffer.class);
		
		logger.info(">>> Updated JobOffer: " + jobOffer);
		
		assertNotNull("REST Data Service failed!", jobOffer);
	}	
}
