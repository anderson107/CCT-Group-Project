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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
