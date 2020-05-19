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
//these annotations are used to perform a join table operation in our database
@Table(name="emp_seachange_training")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.employee",
			joinColumns = @JoinColumn(name = "employee_id")),
	@AssociationOverride(name = "primaryKey.seachangeTraining",
			joinColumns = @JoinColumn(name = "training_id"))
})
public class EmployeeSeaChange extends EmployeeTraining {

	@EmbeddedId
	private EmployeeSeaChangeCompositeID primaryKey = new EmployeeSeaChangeCompositeID();
	
	public EmployeeSeaChange() {
		this.setStatusChoicebox(null);
		this.setDate(null);
		this.setStatus("Pending");
		this.setDatePicker(null);
	}
	
	@Transient
	private final String className = "Sea Change";
	
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
	public SeaChangeTraining getTraining() {
		return primaryKey.getTraining();
	}
	
	@Override
	public void setTraining(TrainingSuperClass training) {
		primaryKey.setTraining(training);;
	}
}
