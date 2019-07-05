package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.ejb.CompanyService;
import org.app.service.ejb.CompanyServiceEJB;
import org.app.service.ejb.JobSeekerService;
import org.app.service.ejb.JobSeekerServiceEJB;
import org.app.service.entities.Company;
import org.app.service.entities.Employee;
import org.app.service.entities.JobOffer;
import org.app.service.entities.JobSeeker;
import org.app.service.entities.Message;
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
public class TestCompanyServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestCompanyServiceEJBArq.class.getName());
	
	@EJB@Inject
	private static CompanyService service;
	@EJB@Inject
	private static JobSeekerService jobSeekerService;
	
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
	public void test1_addCompany() throws Exception {
		logger.info("DEBUG: Junit TESTING: addCompany ...");
		
		Integer nrOfCompany = 5;
		for(int i=1;i<=nrOfCompany;i++) {
			service.addCompany(new Company(null,"Company_"+i));
		}
		Collection<Company> companies = service.getCompanies();
		assertTrue("Fail to add company!",companies.size() == nrOfCompany);
	}
	
	//@Test
	public void test2_deleteCompany() throws Exception {
		logger.info("DEBUG: Junit TESTING: deleteCompany...");
		
		Company company = service.getCompanyByName("Company_1");
		service.removeCompany(company);
		Collection<Company> companyAfterDelete = service.getCompanies();
		assertTrue("Fail to delete company!",companyAfterDelete.size()==4);		
	}
	
	//@Test
	public void test3_getCompany() throws Exception {
		logger.info("DEBUG: Junit TESTING: getCompanies...");
		
		Collection<Company> companies = service.getCompanies(); 
		assertTrue("Fail to get all companies!",companies.size()!=0);
	}
	
	//@Test
	public void test4_getCompanyByName() throws Exception {
		logger.info("DEBUG: Junit TESTING: getCompanyByName...");
		
		Company company	= service.getCompanyByName("Company_2");
		assertTrue("Fail to get company by name!",company.getIdUser() ==2);
	}
	//@Test
	public void test5_saySomething() throws Exception {
		logger.info("DEBUG: Junit TESTING: saySomething...");
		service.saySomething();
	}
	//@Test
	public void test6_getCompanyById() throws Exception {
		logger.info("DEBUG: Junit TESTING: getCompanyByID...");
		
		Company company = service.getCompanyById((long) 64);
		assertTrue("Fail to get Company By ID",company.getIdUser() == 64);
	}
	//@Test
	public void test7_addCompanyAndMessages() throws Exception {
		logger.info("DEBUG: Junit TESTING: addCompanyAndMessages...");
		
		Company company = service.addCompany(new Company(null,"MyCompany"));
		Integer nrMessage = 5;
		List<Message> listMessages = new ArrayList<Message>();
		for(int i=1;i<=nrMessage;i++)
		{
			Message message = new Message(null,"title_"+i,"text_"+i,company,null);
			listMessages.add(message);
		}
		company.setListMessages(listMessages);
		company.setCompanyDescription("asdas");
		company = service.addCompany(company);
		
		assertTrue("Fail to add Company and Message",company.getListMessages().size()!=0);
	}
	//@Test
	public void test8_addJobOfferToCompany() throws Exception {
		logger.info("DEBUG: Junit TESTING: addJobOfferToCompany...");
		Integer nrOfJobOffer = 3;
		Company company = service.getCompanyById((long) 6);
		for(int i=1;i<=nrOfJobOffer;i++)
		{
			service.addJobOfferToCompany(company, new JobOffer(null,"work and not cry"));
		}
		assertTrue("Fail to add JobOffer to Company",service.getCompanyById(company.getIdUser()).getListJobOffer().size()!=0);
	}
	//@Test
	public void test9_addEmployeToCompany() throws Exception {
		logger.info("DEBUG: Junit TESTING: addEmployeToCompany..");

		Company curentCompany = service.getCompanyById((long) 6);
		JobSeeker jobSeeker = jobSeekerService.getById((long)7);
		Employee employee = service.addEmloyeeToCompany(curentCompany, jobSeeker,null);
		
		assertTrue("Fail to add Employe to Company",employee != null);
	}
}
