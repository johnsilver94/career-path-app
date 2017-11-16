package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Company;
import org.app.service.entities.EntityBase;

@Remote
public interface CompanyService {

	Company addCompany(Company companyToAdd) throws Exception;
	
	String  removeCompany(Company companyToDelete) throws Exception;
	
	Company getCompanyById(Integer companyId) throws Exception;
	
	Collection<Company> getCompanies() throws Exception;
}
