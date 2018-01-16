package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name="skill")
@XmlAccessorType(XmlAccessType.NONE)
public class Skill implements Serializable {
	@Id 
	@SequenceGenerator(name = "SEQ_Skill", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Skill")
	@GeneratedValue(generator = "SEQ_Skill", strategy = SEQUENCE)
	Long idSkill;
	String Description;
	@XmlElement
	public Long getIdSkill() {
		return idSkill;
	}
	public void setIdSkill(Long idSkill) {
		this.idSkill = idSkill;
	}
	@XmlElement
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public static String BASE_URL = "http://localhost:8080/SAM/data/skills";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL 
				+ "/"
				+ this.getIdSkill();
        return new AtomLink(restUrl, "get-jobOffers");
    }	
	public void setLink(AtomLink link){}
	
}
