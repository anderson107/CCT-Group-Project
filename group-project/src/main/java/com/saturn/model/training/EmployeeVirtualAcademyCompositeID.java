package com.saturn.model.training;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.saturn.model.employee.Employee;

//this class creates a composite key in the database, employee primary key with training primary key
@SuppressWarnings("serial")
@Embeddable
public class EmployeeVirtualAcademyCompositeID implements Serializable{

	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private VirtualAcademyTraining virtualTraining;

	public VirtualAcademyTraining getTraining() {
		return this.virtualTraining;
	}

	public void setTraining(TrainingSuperClass training) {
		this.virtualTraining = (VirtualAcademyTraining) training;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee emp) {
		this.employee = emp;
	}
}
