package com.saturn.model.training;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainingReportObject {

	private String firstName;
	
	private String lastName;
	
	private String training;
	
	private String className;
	
	private String status;
	
	private LocalDate date;

	public TrainingReportObject(String firstName, String lastName, String training, String className, String status,
			LocalDate date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.training = training;
		this.className = className;
		this.status = status;
		this.date = date;
	}
	
	
}
