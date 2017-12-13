package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.ejb.JobSeekerService;
import org.app.service.ejb.JobSeekerServiceEJB;
import org.app.service.ejb.UserService;
import org.app.service.entities.CV;
import org.app.service.entities.CareerLevel;
import org.app.service.entities.JobSeeker;
import org.app.service.entities.Users;
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
public class TestJobSeekerServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestJobSeekerServiceEJBArq.class.getName());
	
	@EJB @Inject
	private static JobSeekerService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class)
	                .addPackage(Users.class.getPackage())
	                .addClass(JobSeeker.class)
	                .addClass(JobSeekerService.class)
	                .addClass(JobSeekerServiceEJB.class)
	                .addClass(CV.class)
	                .addClass(EntityRepository.class)
	                .addClass(EntityRepositoryBase.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	   }
	//@Test
	public void test1_addJobSeeker() throws Exception {
		logger.info("DEBUG: Junit TESTING: addJobSeeker ...");
		
		Integer nrJobSeeker =3;
		for(int i=1;i<=nrJobSeeker;i++) 
		{
			service.add(new JobSeeker(null,20,"Nume_"+i,"Prenume_"+i,null,null,null,null));
		}
		assertTrue("Fail to add JobSeeker!",service.toCollection().size()>=0);
	}
	//@Test
	public void test2_addCVToJobSeeker() throws Exception {
		logger.info("DEBUG: Junit TESTING: addCVToJobSeeker ...");
		
		JobSeeker jobSeeker = service.getById((long)7);
		CV cvToAdd = new CV(null,CareerLevel.ENTRY_LEVEL,"Be the best in the world",null,null,null,null,null);
		cvToAdd = service.addCvToJobSeeker(jobSeeker, cvToAdd);
		
		assertTrue("Fail to add JobSeeker!",cvToAdd.getJobSeeker() !=null);
	}
	
}
