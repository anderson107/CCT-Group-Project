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
@Table(name="hse")
@NoArgsConstructor
public class HSETraining extends TrainingSuperClass implements Training {

	@OneToMany(mappedBy = "primaryKey.hsetraining", cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, fetch= FetchType.LAZY)
	private List<EmployeeHSE>employeehse;
	
	public HSETraining(String training) {
		this.setTraining(training);
		this.setCreationDate(LocalDate.now());
		this.setClassName(null);
		this.setCheckbox(null);
	}
	
	@Override
	public void addTraining(Employee emp) {

		
	}

}
