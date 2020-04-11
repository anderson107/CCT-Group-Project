package com.saturn.model.training;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.exolab.castor.types.DateTime;

import com.controller.DatePickerConverter;

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
	
	@Transient
	private String className;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@Transient
	private CheckBox checkbox;

	@Override
	public String toString() {
		return "" + training;
	}
	
	public TrainingSuperClass(String training) {
		this.training = training;
		this.setCreationDate(LocalDate.now());
		this.setClassName(null);
		this.setCheckbox(null);
	}
	
}
