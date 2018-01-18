package org.app.service.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Company;
import org.app.service.entities.Employee;
import org.app.service.entities.JobOffer;
import org.app.service.entities.JobSeeker;
import org.app.service.entities.Position;
import org.jboss.arquillian.core.api.annotation.Inject;

@Path("companies")
@Stateless @LocalBean
public class CompanyServiceEJB implements CompanyService {
	private static Logger logger = Logger.getLogger(CompanyServiceEJB.class.getName());

	@PersistenceContext(unitName="SAM1")//@Inject
 	private EntityManager em;
	private EntityRepository<Employee> employeeService;
	
	public CompanyServiceEJB() {	
	}
	
    @PostConstruct
	public void init(){
    	employeeService = new EntityRepositoryBase<Employee>(this.em,Employee.class);
		logger.info("Initialized :");
	}	
	
	
	@PUT @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Company addCompany(Company companyToAdd) throws Exception {
		System.out.println("em = " + em);
			if(companyToAdd.getIdUser() == null || this.getCompanyById(companyToAdd.getIdUser()) == null)
			{
				em.persist(companyToAdd);
				em.flush();
				em.refresh(companyToAdd);
			}
			else 
			{
				em.merge(companyToAdd);
			}
			return companyToAdd;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<Company> addIntoCompanies(Company company) throws Exception
	{
		this.addCompany(company);
		return this.getCompanies();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Collection<Company> removeCompany(Company companyToDelete) throws Exception {
		companyToDelete = em.merge(companyToDelete);
		em.remove(companyToDelete);
		em.flush();
		return this.getCompanies();
	}
	
	@DELETE @Path("/{id}") 
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String remove(@PathParam("id")Long id) throws Exception {
		Company company = this.getCompanyById(id);
		em.remove(company);
		return "True";
	}
	@Override
	public Company getCompanyByName(String companyName) throws Exception {
		return em.createQuery("SELECT c FROM Company c WHERE c.companyName = :name", Company.class)
			   .setParameter("name", companyName)
			   .getResultList().get(0);
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<Company> getCompanies() throws Exception {
		List<Company> companies = em.createQuery("SELECT c FROM Company c",Company.class)
						.getResultList();
		return companies;
	}
	
	@GET @Path("/companyData")
	@Produces({MediaType.TEXT_PLAIN})
	@Override
	public String saySomething() throws Exception {
		return "Hello!!";
	}

	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Company getCompanyById(@PathParam("id")Long id) throws Exception {
		return em.createQuery("SELECT c FROM Company c WHERE c.idUser = :id",Company.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public JobOffer addJobOfferToCompany(Company selectedCompany, JobOffer jobOfferToAdd) throws Exception {
		jobOfferToAdd.setCompany(selectedCompany);
		selectedCompany.getListJobOffer().add(jobOfferToAdd);
		this.addCompany(selectedCompany);
		return jobOfferToAdd;
	}

	@Override
	public Employee addEmloyeeToCompany(Company curentCompany, JobSeeker jobSeekerToAdd, Position acupiedPosition) throws Exception {
		return employeeService.add(new Employee(null,jobSeekerToAdd,acupiedPosition,curentCompany));
	}
	
	@POST @Path("new/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Company createNewCompany(@PathParam("id")Long id) throws Exception
	{
		Company company = new Company(id,"Company_"+id);
		List<JobOffer> jobOffers = new ArrayList<JobOffer>();
		Integer nrJobOffer=5;
		
		for(int i=1;i<nrJobOffer;i++) 
		{
			jobOffers.add(new JobOffer(null,"responsability_"+nrJobOffer));
		}
		company.setListJobOffer(jobOffers);
		
		this.addCompany(company);
		return company;
	}
}
