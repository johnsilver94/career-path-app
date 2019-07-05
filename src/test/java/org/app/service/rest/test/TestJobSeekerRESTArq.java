package org.app.service.rest.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.ejb.JobSeekerService;
import org.app.service.ejb.JobSeekerServiceEJB;
import org.app.service.entities.Company;
import org.app.service.entities.JobSeeker;
import org.jboss.arquillian.container.test.api.Deployment;
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
public class TestJobSeekerRESTArq {
	private static Logger logger = Logger.getLogger(TestJobSeekerRESTArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/SAM/data/jobSeekers";	

	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class)
	                .addPackage(JobSeeker.class.getPackage())
	                .addClass(JobSeekerService.class)
	                .addClass(JobSeekerServiceEJB.class)
	                .addClass(EntityRepository.class)
	                .addClass(EntityRepositoryBase.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	   }

	@Test
	public void test1_getJobSeekers() throws Exception{
		logger.info("DEBUG: Junit TESTING: test1_getJobSeekers ...");
		Collection<JobSeeker> jobSeekers = ClientBuilder.newClient()
				.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<JobSeeker>>() {});
		assertTrue("Fail to read Companies!", jobSeekers.size() >0);
		jobSeekers.stream().forEach(System.out::println);
	}
	@Test
	public void test2_AddJobSeeker() {
		logger.info("DEBUG: Junit TESTING: test2_AddJobSeeker ...");
		Client client = ClientBuilder.newClient();
		Collection<JobSeeker> jobSeekers;
		Integer nrOfJobSeeker = 5;
		JobSeeker jobSeeker;
		for(int i=1;i<=nrOfJobSeeker;i++) {
			jobSeeker = new JobSeeker(null,"User_"+i,"Pass_"+i);
			jobSeekers = client.target(serviceURL)
					.request().accept(MediaType.APPLICATION_JSON)
					.post(Entity.entity(jobSeeker, MediaType.APPLICATION_JSON))
					.readEntity(new GenericType<Collection<JobSeeker>>() {});
			assertTrue("Fail to read Company!",jobSeekers.size() >= 1);
		}
		jobSeekers = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<JobSeeker>>() {});
		assertTrue("Fail to add Companies!",jobSeekers.size() >= nrOfJobSeeker);
		jobSeekers.stream().forEach(System.out::println);
	}
	@Test
	public void test3_DeleteJobSeeker() {
		String resourceURL = serviceURL + "/127";
		logger.info("DEBUG: Junit TESTING: test3_DeleteJobSeeker ...");
		Client client = ClientBuilder.newClient();
		String Response;
	    Response = client.target(resourceURL).request().delete().readEntity(String.class);
	    
	    
	    logger.info(">>>>>> DEBUG: REST Response ..." + Response);
	    assertTrue("Is not deleted!", Response.contains("True"));
	}
	@Test
	public void test4_GetByID() {
		String resourceURL = serviceURL + "/8";
		logger.info("DEBUG: Junit TESTING: t4_GetByID ...");
		
		JobSeeker jobSeeker = ClientBuilder.newClient().target(resourceURL)
				.request().accept(MediaType.APPLICATION_JSON)
				.get().readEntity(JobSeeker.class);
		
		assertNotNull("REST Data Service failed!", jobSeeker);
		logger.info(">>>>>> DEBUG: REST Response ..." + jobSeeker);
	}
	@Test
	public void test5_UpdateJobSeeker() {
		String resourceURL = serviceURL + "/9";
		logger.info("************* DEBUG: Junit TESTING: test5_UpdateJobSeeker ... :" + resourceURL);
		Client client = ClientBuilder.newClient();
		
		JobSeeker jobSeeker = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_XML)
				.get().readEntity(JobSeeker.class);
		
		assertNotNull("REST Data Service failed!", jobSeeker);
		logger.info(">>> Initial JobSeeker: " + jobSeeker);
		
		jobSeeker.setPassWord("newPassWord");
		jobSeeker = client.target(resourceURL)
				.request().accept(MediaType.APPLICATION_XML).header("Content-Type", "application/xml")
				//.request().accept(MediaType.APPLICATION_JSON)
				.put(Entity.entity(jobSeeker, MediaType.APPLICATION_XML))
				.readEntity(JobSeeker.class);
		
		logger.info(">>> Updated JobSeeker: " + jobSeeker);
		
		assertNotNull("FINAL REST Data Service failed!", jobSeeker);
	}

}
