package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Company;

@Remote
public interface CompanyService {

	Company addCompany(Company companyToAdd) throws Exception;
	
	String  removeCompany(Company companyToDelete) throws Exception;
	
	Company getCompanyByName(String companyName) throws Exception;
	
	Collection<Company> getCompanies() throws Exception;

	String saySomething() throws Exception;
}
