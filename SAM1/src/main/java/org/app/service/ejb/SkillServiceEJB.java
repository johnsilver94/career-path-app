package org.app.service.ejb;

import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Skill;

@Path("skills")
@Stateless @LocalBean
public class SkillServiceEJB extends EntityRepositoryBase<Skill> implements SkillService {
	private static Logger logger = Logger.getLogger(SkillServiceEJB.class.getName());
	
	//@EJB
	private EntityRepository<Skill> skillRepository;
	
    @PostConstruct
	public void init(){
    	skillRepository	= new EntityRepositoryBase<Skill>(this.em,Skill.class);
		logger.info("Initialized :");		
	}
    
	@DELETE @Path("/{id}") 
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String remove(@PathParam("id")Long id) throws Exception {
		Skill skill = this.getSkilById((long)id);
		this.remove(skill);
		return "True";
	}

	@PUT @Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Skill addSkill(Skill skillToAdd) throws Exception {
		if(skillToAdd.getIdSkill() == null || this.getSkilById(skillToAdd.getIdSkill()) == null)
		{
			em.persist(skillToAdd);
			em.flush();
			em.refresh(skillToAdd);
		}
		else 
		{
			em.merge(skillToAdd);
		}
		return skillToAdd;
	}

	@DELETE
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Collection<Skill> removeSkill(Skill skillToDelete) throws Exception {
		skillToDelete = this.refresh(skillToDelete);
		this.remove(skillToDelete);
		return this.toCollection();
	}

	@GET @Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Skill getSkilById(@PathParam("id")Long skillId) throws Exception {
		return this.getById(skillId);
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<Skill> getSkills() throws Exception {
		return this.toCollection();
	}

	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Override
	public Collection<Skill> addIntoSkills(Skill skillToAdd) throws Exception {
		this.addSkill(skillToAdd);
		return this.toCollection();
	}

}
