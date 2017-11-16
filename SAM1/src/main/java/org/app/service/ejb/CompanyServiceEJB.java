package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Company;
import org.jboss.arquillian.core.api.annotation.Inject;

@Stateless @LocalBean
public class CompanyServiceEJB implements CompanyService {
	private static Logger logger = Logger.getLogger(CompanyServiceEJB.class.getName());

	@PersistenceContext(unitName="OracleDS")
 	private static EntityManager em;
	
	@Inject
	UserTransaction utx;
	
	public CompanyServiceEJB() {
		
	}
	
    @PostConstruct
	public void init(){
		logger.info("Initialized :");
	}	
	
	@Override
	public Company addCompany(Company companyToAdd) throws Exception {
		utx.begin();
		em.persist(companyToAdd);
		em.flush();
		em.refresh(companyToAdd);
		utx.commit();
		return companyToAdd;
	}

	@Override
	public String removeCompany(Company companyToDelete) throws Exception {
		utx.begin();
		companyToDelete = em.merge(companyToDelete);
		em.remove(companyToDelete);
		em.flush();
		utx.commit();
		return "True";
	}

	@Override
	public Company getCompanyById(Integer companyId) throws Exception {
		return em.createQuery("SELECT u FROM Users WHERE u.id = :id", Company.class)
			   .setParameter("id", companyId)
			   .getSingleResult();
	}

	@Override
	public Collection<Company> getCompanies() throws Exception {
		utx.begin();
		List<Company> companies = em.createQuery("SELECT c FROM Company",Company.class)
						.getResultList();
		utx.commit();
		return companies;
	}
}
