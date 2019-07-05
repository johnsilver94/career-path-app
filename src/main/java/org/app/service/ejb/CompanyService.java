package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.app.service.entities.Company;
import org.app.service.entities.Employee;
import org.app.service.entities.JobOffer;
import org.app.service.entities.JobSeeker;
import org.app.service.entities.Position;

@Remote
public interface CompanyService {

	Company addCompany(Company companyToAdd) throws Exception;
	
	Collection<Company>  removeCompany(Company companyToDelete) throws Exception;
	

	String remove(Long id) throws Exception ;
	
	Company getCompanyByName(String companyName) throws Exception;
	
	Company getCompanyById(Long id) throws Exception;
	
	Collection<Company> getCompanies() throws Exception;

	String saySomething() throws Exception;
	
	JobOffer addJobOfferToCompany(Company company,JobOffer jobOfferToAdd) throws Exception;
	
	Employee addEmloyeeToCompany(Company curentCompany,JobSeeker jobSeekerToAdd, Position acupiedPosition) throws Exception;

	Collection<Company> addIntoCompanies(Company company) throws Exception;
	
}
