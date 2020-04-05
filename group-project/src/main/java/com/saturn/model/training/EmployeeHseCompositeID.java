package com.saturn.model.training;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.saturn.model.employee.Employee;

@SuppressWarnings("serial")
@Embeddable
public class EmployeeHseCompositeID implements Serializable {

	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	private Employee employee;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private HSETraining hsetraining;
	
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee emp) {
		this.employee = emp;
	}

	public HSETraining getTraining() {
		return this.hsetraining;
	}

	public void setTraining(TrainingSuperClass training) {
		this.hsetraining = (HSETraining) training;
	}
	
}
