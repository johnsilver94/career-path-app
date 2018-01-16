
package org.app.service.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.DiscriminatorValue;
import static javax.persistence.FetchType.LAZY;
@XmlRootElement(name="jobSeeker") 
@XmlAccessorType(XmlAccessType.NONE)
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
	Preference preference;
	@OneToMany(cascade = ALL, orphanRemoval = true)
	List<Skill> pathTestSkill;
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
	@XmlElement
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public List<Skill> getPathTestSkill() {
		return pathTestSkill;
	}
	public void setPathTestSkill(List<Skill> pathTestSkill) {
		this.pathTestSkill = pathTestSkill;
	}
	public JobSeeker(Long idUser, String userName, String passWord) {
		super(idUser, userName, passWord);
		// TODO Auto-generated constructor stub
	}
	public JobSeeker(Long idUser) {
		super(idUser);
		// TODO Auto-generated constructor stub
	}
	public JobSeeker(Long idUser, Integer age, String name, String surname, Preference preference,
			List<Skill> pathTestSkill, CV cv, List<JobOffer> listJobOfferAplication) {
		super(idUser);
		this.age = age;
		this.name = name;
		this.surname = surname;
		this.preference = preference;
		this.pathTestSkill = pathTestSkill;
		this.cv = cv;
		this.listJobOfferAplication = listJobOfferAplication;
	}
	public static String BASE_URL = "http://localhost:8080/SAM/data/jobSeekers";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL 
				+ "/"
				+ this.getIdUser();
        return new AtomLink(restUrl, "get-jobSeekers");
    }	
	public void setLink(AtomLink link){}
}
