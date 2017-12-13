package org.app.service.rest.test;

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
import org.app.service.ejb.CompanyService;
import org.app.service.ejb.CompanyServiceEJB;
import org.app.service.ejb.JobSeekerService;
import org.app.service.ejb.JobSeekerServiceEJB;
import org.app.service.entities.Company;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCompanyDataServiceRESTArq {
	private static Logger logger = Logger.getLogger(TestCompanyDataServiceRESTArq.class.getName());
	
	private static String serviceURL = "http://localhost:8080/SAM/data/companies";	

	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class)
	                .addPackage(Company.class.getPackage())
	                .addClass(CompanyService.class)
	                .addClass(CompanyServiceEJB.class)
	                .addClass(JobSeekerService.class)
	                .addClass(JobSeekerServiceEJB.class)
	                .addClass(EntityRepository.class)
	                .addClass(EntityRepositoryBase.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	   }
	
	@Test
	public void test1_saySomething() throws Exception {
		String resourceURL = serviceURL + "/companyData";
		logger.info("DEBUG: Junit TESTING: saySomething...");
		String response = ClientBuilder.newClient()
					.target(resourceURL)
					.request().get()
					.readEntity(String.class);
		assertNotNull("Fail to read Company!",response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test2_getCompanies() throws Exception{
		logger.info("DEBUG: Junit TESTING: test2_getCompanies ...");
		Collection<Company> companies = ClientBuilder.newClient()
				.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Company>>() {});
		assertTrue("Fail to read Companies!", companies.size() >0);
		companies.stream().forEach(System.out::println);
	}
	@Test
	public void test3_AddCompany() {
		logger.info("DEBUG: Junit TESTING: test3_AddCompany ...");
		Client client = ClientBuilder.newClient();
		Collection<Company> companies;
		Integer nrOfCompany = 5;
		Company company;
		for(int i=1;i<=nrOfCompany;i++) {
			company = new Company(null,"Company_"+i);
			companies = client.target(serviceURL)
					.request().accept(MediaType.APPLICATION_JSON)
					.post(Entity.entity(company, MediaType.APPLICATION_JSON))
					.readEntity(new GenericType<Collection<Company>>() {});
			assertTrue("Fail to read Company!",companies.size() >= nrOfCompany);
		}
		companies = client.target(serviceURL)
				.request().get()
				.readEntity(new GenericType<Collection<Company>>() {});
		assertTrue("Fail to add Companies!",companies.size() >= nrOfCompany);
		companies.stream().forEach(System.out::println);
	}
}
