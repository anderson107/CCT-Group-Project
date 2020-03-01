package com.saturn.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String DOB;
	private String mobile;
	private String telephone;
	private String address;
	private String city;
	private String position;
	private String password;

}
