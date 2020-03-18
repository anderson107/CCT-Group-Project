package com.saturn.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	}

	
}
