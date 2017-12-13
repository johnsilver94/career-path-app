package org.app.service.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.ManyToOne;

@Entity
public class Employee implements Serializable {
	@Id
	@SequenceGenerator(name = "SEQ_Employee", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_Employee")
	@GeneratedValue(generator = "SEQ_Employee", strategy = SEQUENCE)
	Long idEmployee;
	@OneToOne
	JobSeeker employee;
	@OneToOne
	Position position;
	@ManyToOne
	Company company;
	public Long getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}
	public JobSeeker getEmployee() {
		return employee;
	}
	public void setEmployee(JobSeeker employee) {
		this.employee = employee;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(Long idEmployee, JobSeeker employee, Position position, Company company) {
		super();
		this.idEmployee = idEmployee;
		this.employee = employee;
		this.position = position;
		this.company = company;
	}
}
