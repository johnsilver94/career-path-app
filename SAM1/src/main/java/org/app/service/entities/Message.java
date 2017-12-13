package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public class Message implements Serializable{
	@Id
	@SequenceGenerator(name = "SEQ_Messages", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Messages")
	@GeneratedValue(generator = "SEQ_Messages", strategy = SEQUENCE)
	Long idMessage;
	String  title;
	String  text;
	@ManyToOne
	Users fromUser;
	@ManyToMany
	List<Users> toUserList;
	
	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Users getFromUser() {
		return fromUser;
	}

	public void setFromUser(Users fromUser) {
		this.fromUser = fromUser;
	}

	public List<Users> getToUserList() {
		return toUserList;
	}

	public void setToUserList(List<Users> toUserList) {
		this.toUserList = toUserList;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(Long idMessage, String title, String text, Users fromUser, List<Users> toUserList) {
		super();
		this.idMessage = idMessage;
		this.title = title;
		this.text = text;
		this.fromUser = fromUser;
		this.toUserList = toUserList;
	}

	public Message(Long idMessage, String title, String text) {
		super();
		this.idMessage = idMessage;
		this.title = title;
		this.text = text;
	}
	
}
