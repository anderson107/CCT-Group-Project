package com.saturn.model.training;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.saturn.model.employee.Employee;

import lombok.Data;

@Data
@Entity
@Table(name="emp_hse_training")
public class EmployeeHSE {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="training_id")
	private HSETraining hseTraining;
	
	@Column(name="status")
	private String status;
	
	@Column(name="date")
	private LocalDate date;
}
