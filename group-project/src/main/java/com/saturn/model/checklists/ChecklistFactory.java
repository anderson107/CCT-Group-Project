package com.saturn.model.checklists;

public class ChecklistFactory {

	public static ChecklistSuperClass create(String itemDescription, String status, String frequency, String type) {
		
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
