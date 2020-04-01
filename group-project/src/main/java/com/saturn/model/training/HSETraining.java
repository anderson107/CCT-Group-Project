package com.saturn.model.training;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.saturn.model.employee.Employee;

import lombok.NoArgsConstructor;

@Entity
@Table(name="hse")
@NoArgsConstructor
public class HSETraining extends TrainingSuperClass implements Training {

	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="emp_hse_training",
			joinColumns=@JoinColumn(name="employee_id"),
			inverseJoinColumns=@JoinColumn(name="training_id")
			)
	private List<Employee>hseList;
	
	public HSETraining(String training) {
		this.setTraining(training);
		this.setCreationDate(LocalDate.now());
		this.setClassName(null);
		this.setCheckbox(null);
	}
	
	@Override
	public void addTraining(Employee emp) {

		if(hseList==null) {
			hseList = new ArrayList<>();
			hseList.add(emp);
		}else {
			hseList.add(emp);
		}
	}

}
