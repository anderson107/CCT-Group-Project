package com.saturn.model.checklists;

import java.time.LocalDate;

public class ChecklistFactory {

	public static ChecklistSuperClass create(String itemDescription, String status, String frequency, String type, LocalDate date) {
		
		switch(type) {
		case "Fire Warden":
			FireWarden firewarden = new FireWarden(itemDescription, status, frequency);
			firewarden.setDueDate(date);
			return firewarden;
		case "Health and Safety":
			HealthSafetyChecklist check = new HealthSafetyChecklist(itemDescription, status, frequency);
			check.setDueDate(date);
			return check;
		case "Floor HACCP":
			FloorHACCP floor = new FloorHACCP(itemDescription, status, frequency);
			floor.setDueDate(date);
			return floor;
		case "Coffee HACCP":
			CoffeeHACCP coffee = new CoffeeHACCP(itemDescription, status, frequency);
			coffee.setDueDate(date);
			return coffee;
		case "Deli HACCP":
			DeliHACCP deli = new DeliHACCP(itemDescription, status, frequency);
			deli.setDueDate(date);
			return deli;
		}
		return null;
	}

}
