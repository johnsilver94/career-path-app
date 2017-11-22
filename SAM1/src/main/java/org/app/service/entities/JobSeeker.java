
package org.app.service.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.DiscriminatorValue;
@Entity
//@DiscriminatorValue("JobSeeker")
public class JobSeeker extends Users{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer age;
	String  name;
	String 	surname;
	@OneToOne
	CV cv;
	@ManyToMany
	List<JobOffer> listJobOfferAplication;
	public JobSeeker(Long idUser, String name) {
		super(idUser);
		this.name = name;
	}
	public JobSeeker() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobSeeker(Long idUser, String userName, String passWord) {
		super(idUser, userName, passWord);
		// TODO Auto-generated constructor stub
	}
	public JobSeeker(Long idUser) {
		super(idUser);
		// TODO Auto-generated constructor stub
	}
}
