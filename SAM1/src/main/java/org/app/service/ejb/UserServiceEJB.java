package org.app.service.ejb;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Company;
import org.app.service.entities.Feedback;
import org.app.service.entities.Location;
import org.app.service.entities.Message;
import org.app.service.entities.Users;

@Path("/users")
@Stateless @LocalBean
public class UserServiceEJB extends EntityRepositoryBase<Users>  implements UserService {
	private static Logger logger = Logger.getLogger(UserServiceEJB.class.getName());
	
	//@EJB
	private EntityRepository<Users> entityRepository;
	private EntityRepository<Message> messageRepository;
	private EntityRepository<Location> locationRepository;
	private EntityRepository<Feedback> feedbackRepository;
	
	
    @PostConstruct
	public void init(){
    	messageRepository	=	new EntityRepositoryBase<Message>(this.em,Message.class);
    	locationRepository	=	new EntityRepositoryBase<Location>(this.em,Location.class);
    	feedbackRepository	=	new EntityRepositoryBase<Feedback>(this.em,Feedback.class);
		logger.info("Initialized :");		
	}	
	
	@Override
	public String addMessage(Message messageToAdd) throws Exception {
		messageRepository.add(messageToAdd);
		return "Succes";
	}

	@Override
	public Location addLocationToUser(Users curentUser,Location locationToAdd) throws Exception {
		curentUser.setLocation(locationToAdd);
		this.refresh(curentUser);
		return locationToAdd;
	}

	@Override
	public Feedback addFeedback(Users curentUser, Feedback feedbackToAdd, Users aboutUser) throws Exception {
		feedbackToAdd.setUser(curentUser);
		feedbackToAdd.setOboutUser(aboutUser);
		feedbackRepository.add(feedbackToAdd);
		return feedbackToAdd;
	}

}
