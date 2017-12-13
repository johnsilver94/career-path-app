package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Feedback;
import org.app.service.entities.Location;
import org.app.service.entities.Message;
import org.app.service.entities.Users;

@Remote
public interface UserService extends EntityRepository<Users>{
	
	String addMessage (Message messageToAdd) throws Exception;
	
	Location addLocationToUser (Users curentUser,Location locationToAdd) throws Exception;
	
	Feedback addFeedback(Users curentUser,Feedback feedbackToAdd, Users aboutUser) throws Exception;
}
