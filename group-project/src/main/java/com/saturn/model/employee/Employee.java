package com.saturn.model.employee;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.saturn.model.training.EmployeeHSE;
import com.saturn.model.training.EmployeeSeaChange;
import com.saturn.model.training.EmployeeVirtualAcademy;

import javafx.scene.control.CheckBox;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="employee")
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dob")
	private LocalDate DOB;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="registration_date")
	private LocalDate creationDate;
	
	@OneToMany(mappedBy = "primaryKey.employee", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<EmployeeHSE>employeehse;
	
	@OneToMany(mappedBy = "primaryKey.employee", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<EmployeeSeaChange>employeeSeaChange;
	
	@OneToMany(mappedBy = "primaryKey.employee", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	private List<EmployeeVirtualAcademy>employeeVirtualAcademy;
	
	@Transient
	private CheckBox checkbox;
	
	public Employee(String firstName, String lastName, String email, LocalDate dOB, String mobile, String telephone,
			String address, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		DOB = dOB;
		this.mobile = mobile;
		this.telephone = telephone;
		this.address = address;
		this.city = city;
		this.creationDate = LocalDate.now();
		this.checkbox = null;
	}

	@Override
	public String toString() {
		return "" + firstName + " " + lastName;
	}
	
	
}
