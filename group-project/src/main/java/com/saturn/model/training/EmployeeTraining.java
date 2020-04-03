package com.saturn.model.training;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.saturn.model.employee.Employee;

import javafx.scene.control.ChoiceBox;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class EmployeeTraining {

	@Column(name="status")
	private String status;
	
	@Column(name="date")
	private LocalDate date;
	
	@Transient
	private ChoiceBox<String> choicebox;
	
	public abstract Employee getEmployee();
	
	public abstract void setEmployee(Employee emp);
	
	public abstract TrainingSuperClass getTraining();
	
	public abstract void setTraining(TrainingSuperClass training);
	
}
