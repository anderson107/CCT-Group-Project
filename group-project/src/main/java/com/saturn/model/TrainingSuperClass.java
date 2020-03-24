package com.saturn.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import javafx.scene.control.CheckBox;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public class TrainingSuperClass {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="training")
	private String training;
	
	@Column(name="status")
	private String status;
	
	@Transient
	private String className;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@Column(name="date")
	private LocalDate date;
	
	@Transient
	private CheckBox checkbox;
}
