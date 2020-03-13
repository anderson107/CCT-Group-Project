package com.saturn.model;

import java.time.LocalDate;
import java.util.Date;

import javafx.scene.control.CheckBox;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChecklistSuperClass {

	private int id;
	private CheckBox checkbox;
	private String itemDescription;
	private String frequency;
	private String status;
	private LocalDate creationDate;
	private LocalDate dueDate; 
}
