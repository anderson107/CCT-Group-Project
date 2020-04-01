package com.saturn.model.factory.checklist_factory;

import com.saturn.model.checklists.ChecklistSuperClass;
import com.saturn.model.checklists.FireWarden;
import com.saturn.model.checklists.HealthSafetyChecklist;
import com.saturn.model.checklists.Task;

public class Factory {

	public static ChecklistSuperClass getItem(String itemDescription, String status, String frequency, String type) {
		
		switch(type) {
		case "Fire Warden":
			return new FireWarden(itemDescription, status, frequency);
		case "Health and Safety":
			return new HealthSafetyChecklist(itemDescription, status, frequency);
		case "Task":
			return new Task(itemDescription, status, frequency);
		}
		return null;
	}
}
