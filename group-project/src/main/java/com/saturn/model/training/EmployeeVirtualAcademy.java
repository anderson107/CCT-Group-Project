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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="emp_virtual_training")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.employee",
			joinColumns = @JoinColumn(name = "employee_id")),
	@AssociationOverride(name = "primaryKey.virtualTraining",
			joinColumns = @JoinColumn(name = "training_id"))
})
public class EmployeeVirtualAcademy {
    
	@EmbeddedId
	private EmployeeVirtualAcademyCompositeID primaryKey = new EmployeeVirtualAcademyCompositeID();
	
	@Column(name="status")
	private String status;
	
	@Column(name="date")
	private LocalDate date;
	
	@Transient
	public Employee getEmployee() {
		return primaryKey.getEmployee();
	}
	
	public void setEmployee(Employee emp) {
		primaryKey.setEmployee(emp);
	}
	
	@Transient
	public VirtualAcademyTraining getVirtualAcademy() {
		return primaryKey.getVirtualTraining();
	}
	
	public void setVirtualAcademyTraining(VirtualAcademyTraining training) {
		primaryKey.setVirtualTraining(training);
	}
}
