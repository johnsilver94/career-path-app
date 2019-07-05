package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Skill;

@Remote
public interface SkillService extends EntityRepository<Skill>{

	Skill addSkill(Skill skillToAdd) throws Exception;
	
	Collection<Skill> removeSkill(Skill skillToDelete) throws Exception;
	
	Skill getSkilById(Long skillId) throws Exception;
	
	Collection<Skill> getSkills() throws Exception;
	
	Collection<Skill> addIntoSkills(Skill skillToAdd) throws Exception;
}
