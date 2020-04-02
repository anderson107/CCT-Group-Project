package com.saturn.model.training;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.saturn.model.employee.Employee;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Embeddable
public class EmployeeHseCompositeID implements Serializable {

	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private HSETraining hsetraining;
	
}
