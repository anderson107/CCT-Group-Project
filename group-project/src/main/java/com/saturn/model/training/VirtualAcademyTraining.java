package com.saturn.model.training;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.saturn.model.employee.Employee;

import lombok.NoArgsConstructor;

@Entity
@Table(name="virtual_academy")
@NoArgsConstructor
public class VirtualAcademyTraining extends TrainingSuperClass implements Training {
	
	@OneToMany(mappedBy = "primaryKey.virtualTraining", cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, fetch= FetchType.LAZY)
	private List<EmployeeVirtualAcademy>employeeVirtualAcademy;
	
	public VirtualAcademyTraining(String training) {
		this.setTraining(training);
		this.setCreationDate(LocalDate.now());
		this.setClassName(null);
		this.setCheckbox(null);
	}
	
	@Override
	public void addTraining(Employee employee) {
		
	}

	
}
