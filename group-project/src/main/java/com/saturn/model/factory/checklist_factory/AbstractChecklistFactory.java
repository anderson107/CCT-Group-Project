package com.saturn.model.factory.checklist_factory;

import java.time.LocalDate;

import com.saturn.model.checklists.ChecklistSuperClass;

import javafx.scene.control.CheckBox;
import lombok.Data;

@Data
public abstract class AbstractChecklistFactory {

	private String itemDescription;
	private String frequency;
	private CheckBox checkbox;
	private String status;
	private LocalDate creationDate;
	private LocalDate dueDate; 
	
	public abstract ChecklistSuperClass createItem();
}
