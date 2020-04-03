package com.saturn.model.training;

import java.time.LocalDate;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.saturn.model.employee.Employee;

import javafx.scene.control.ChoiceBox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="emp_hse_training")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.employee",
			joinColumns = @JoinColumn(name = "employee_id")),
	@AssociationOverride(name = "primaryKey.hsetraining",
			joinColumns = @JoinColumn(name = "training_id"))
})
public class EmployeeHSE extends EmployeeTraining{

	@EmbeddedId
	private EmployeeHseCompositeID primaryKey = new EmployeeHseCompositeID();
		
	@Transient
	private final String className = "HSE";
	
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
	public HSETraining getTraining() {
		return primaryKey.getTraining();
	}
	
	@Override
	public void setTraining(TrainingSuperClass training) {
		primaryKey.setTraining(training);;
	}

}
