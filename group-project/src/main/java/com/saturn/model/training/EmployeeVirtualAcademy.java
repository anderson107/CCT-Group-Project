package com.saturn.model.training;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.saturn.model.employee.Employee;

import lombok.Getter;

@Getter
@Entity
@Table(name ="emp_virtual_training")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.employee",
			joinColumns = @JoinColumn(name = "employee_id")),
	@AssociationOverride(name = "primaryKey.virtualTraining",
			joinColumns = @JoinColumn(name = "training_id"))
})
public class EmployeeVirtualAcademy extends EmployeeTraining{
    
	@EmbeddedId
	private EmployeeVirtualAcademyCompositeID primaryKey = new EmployeeVirtualAcademyCompositeID();
	
	public EmployeeVirtualAcademy() {
		this.setStatusChoicebox(null);
		this.setStatus("Pending");
		this.setDate(null);
		this.setDatePicker(null);
	}
	
	@Transient
	private final String className = "Virtual Academy";
	
	@Transient
	@Override
	public Employee getEmployee() {
		return primaryKey.getEmployee();
	}
	
	@Override
	public void setEmployee(Employee emp) {
		primaryKey.setEmployee(emp);
	}
	
	@Transient
	@Override
	public VirtualAcademyTraining getTraining() {
		return primaryKey.getTraining();
	}
	
	@Override
	public void setTraining(TrainingSuperClass training) {
		primaryKey.setTraining(training);
	}
}
