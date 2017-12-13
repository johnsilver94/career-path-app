package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.ejb.UserService;
import org.app.service.ejb.UserServiceEJB;
import org.app.service.entities.Company;
import org.app.service.entities.Feedback;
import org.app.service.entities.Location;
import org.app.service.entities.Message;
import org.app.service.entities.Users;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestUserServiceEJBArq.class.getName());
	
	@EJB//@Inject
	private static UserService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class)
	                .addPackage(Users.class.getPackage())
	                .addClass(Message.class)
	                .addClass(UserService.class)
	                .addClass(UserServiceEJB.class)
	                .addClass(EntityRepository.class)
	                .addClass(EntityRepositoryBase.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	   }
	//@Test
	public void test1_addMessageToUser() throws Exception {
		logger.info("DEBUG: Junit TESTING: addMessageToUser ...");
		
		Users fromUser = service.getById((long)1);
		List<Users> toUsers =  (List<Users>) service.toCollection();
		
		for(int i=1;i<=3;i++)
		{
			Message mesage = new Message(null,"Title_"+i,"Description_"+i,fromUser,toUsers);
			fromUser.getListMessages().add(mesage);
		}
		service.refresh(fromUser);
		
		assertTrue("Fail to add Message!",service.getById(fromUser.getIdUser()).getListMessages().size()>=0);
	}
	//@Test
	public void test2_addMessage() throws Exception {
		logger.info("DEBUG: Junit TESTING: addMessageToUser ...");
		
		Users fromUser = service.getById((long)1);
		List<Users> toUsers =  (List<Users>) service.toCollection();
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println(toUsers.size());
		toUsers.remove(fromUser);
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println(toUsers.size());
		for(int i=1;i<=3;i++)
		{
			Message mesage = new Message(null,"Title_"+i,"Description_"+i,fromUser,toUsers);
			service.addMessage(mesage);
		}
		assertTrue("Fail to add Message!",service.getById(fromUser.getIdUser()).getListMessages().size()>=0);
	}
	//@Test
	public void test3_addLocationToUser() throws Exception {
		logger.info("DEBUG: Junit TESTING: addLocationToUser ...");
		
		Users curentUser = service.getById((long)1);
		service.addLocationToUser(curentUser, new Location(null,"Country","Region","County","City","Address"));
		
		assertTrue("Fail to add Location!",service.getById(curentUser.getIdUser()).getLocation()!=null);
	}
	//@Test
	public void test4_modifyUser() throws Exception {
		logger.info("DEBUG: Junit TESTING: modifyUser( ...");
		
		Users curentUser = service.getById((long)1);
		curentUser.setPassWord("passWord1");
		service.refresh(curentUser);
		
		assertTrue("Fail to modifyUser!",service.getById(curentUser.getIdUser()).getPassWord()!=null);
	}
	
	@Test
	public void test5_AddFeedback() throws Exception {
		logger.info("DEBUG: Junit TESTING: AddFeedback ...");
		
		Users curentUser = service.getById((long)1);
		Users aboutUser	= service.getById((long)2);
		Feedback feedbackToAdd = new Feedback(null,null,null,"Good!");
		service.addFeedback(curentUser, feedbackToAdd, aboutUser);
		
		//assertTrue("Fail to modifyUser!",service.getById(curentUser.getId()).getListFeedback().size()>=0);
	}
	
}
