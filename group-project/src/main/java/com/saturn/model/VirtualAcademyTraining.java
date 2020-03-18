package com.saturn.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name="virtual_academy")
@NoArgsConstructor
public class VirtualAcademyTraining extends TrainingSuperClass implements Training {

	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="emp_virtual_training",
			joinColumns=@JoinColumn(name="employee_id"),
			inverseJoinColumns=@JoinColumn(name="training_id")
			)
	private List<Employee> virtualAcademy;
	
	public VirtualAcademyTraining(String training, String status) {
		this.setTraining(training);
		this.setStatus(status);
	}
	
	@Override
	public void addTraining(Employee employee) {
		if(virtualAcademy==null) {
			virtualAcademy = new ArrayList<>();
			virtualAcademy.add(employee);
		}else {
			virtualAcademy.add(employee);
		}
	}

	
}
