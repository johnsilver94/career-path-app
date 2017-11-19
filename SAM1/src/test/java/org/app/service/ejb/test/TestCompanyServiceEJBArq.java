package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.CompanyService;
import org.app.service.ejb.CompanyServiceEJB;
import org.app.service.entities.Company;
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
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "SAM1.war")
	                .addPackage(Company.class.getPackage())
	                .addClass(CompanyService.class)
	                .addClass(CompanyServiceEJB.class)
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
	
//	@Test
	public void test2_deleteCompany() throws Exception {
		logger.info("DEBUG: Junit TESTING: deleteCompany...");
		
		Company company = service.getCompanyByName("Company_1");
		service.removeCompany(company);
		Collection<Company> companyAfterDelete = service.getCompanies();
		assertTrue("Fail to delete company!",companyAfterDelete.size()==4);		
	}
	
//	@Test
	public void test3_getCompany() throws Exception {
		logger.info("DEBUG: Junit TESTING: getCompanies...");
		
		Collection<Company> companies = service.getCompanies(); 
		assertTrue("Fail to get all companies!",companies.size()!=0);
	}
	
//	@Test
	public void test4_getCompanyByName() throws Exception {
		logger.info("DEBUG: Junit TESTING: getCompanyById...");
		
		Company company	= service.getCompanyByName("Company_2");
		assertTrue("Fail to get company by id!",company.getId()==2);
	}
	@Test
	public void test5_saySomething() throws Exception {
		logger.info("DEBUG: Junit TESTING: saySomething...");
		service.saySomething();
	}
	
	
}
