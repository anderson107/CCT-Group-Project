package com.saturn.model.checklists;

import java.time.LocalDate;
import java.util.Date;

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
public class ChecklistSuperClass {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Transient
	private CheckBox checkbox;
	
	@Column(name="item_description")
	private String itemDescription;
	
	@Column(name="frequency")
	private String frequency;
	
	@Column(name="status")
	private String status;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@Column(name="date")
	private LocalDate dueDate; 
}
