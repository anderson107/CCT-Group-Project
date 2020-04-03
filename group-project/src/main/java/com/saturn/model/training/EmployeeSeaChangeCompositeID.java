package com.saturn.model.training;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.saturn.model.employee.Employee;


@SuppressWarnings("serial")
@Embeddable
public class EmployeeSeaChangeCompositeID implements Serializable{

	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private SeaChangeTraining seachangeTraining;

	public SeaChangeTraining getTraining() {
		return seachangeTraining;
	}

	public void setTraining(TrainingSuperClass training) {
		this.seachangeTraining = (SeaChangeTraining) training;
		
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee emp) {
		this.employee = emp;
	}
}
