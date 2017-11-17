package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.service.entities.Company;
import org.jboss.arquillian.core.api.annotation.Inject;

@Stateless @LocalBean
public class CompanyServiceEJB implements CompanyService {
	private static Logger logger = Logger.getLogger(CompanyServiceEJB.class.getName());

	@PersistenceContext(unitName="SAM1")//@Inject
 	private EntityManager em;
	
	public CompanyServiceEJB() {	
	}
	
    @PostConstruct
	public void init(){
		logger.info("Initialized :");
	}	
	
	@Override
	public Company addCompany(Company companyToAdd) throws Exception {
		System.out.println("em = " + em);
		em.persist(companyToAdd);
		em.flush();
		em.refresh(companyToAdd);
		return companyToAdd;
	}

	@Override
	public String removeCompany(Company companyToDelete) throws Exception {
		companyToDelete = em.merge(companyToDelete);
		em.remove(companyToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Company getCompanyByName(String companyName) throws Exception {
		return em.createQuery("SELECT u FROM Users u WHERE u.name = :name", Company.class)
			   .setParameter("id", companyName)
			   .getSingleResult();
	}

	@Override
	public Collection<Company> getCompanies() throws Exception {
		List<Company> companies = em.createQuery("SELECT c FROM Company c",Company.class)
						.getResultList();
		return companies;
	}

	@Override
	public String saySomething() throws Exception {
		return "Hello!!";
	}
}
