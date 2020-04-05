package com.saturn.model.checklists;

public enum ChecklistCategory {

	FIRE_WARDEN("Fire Warden"),
	HEALTH_SAFETY("Health and Safety"),
	COFFEE_HACCP("Coffee HACCP"),
	DELI_HACCP("Deli HACCP"),
	FLOOR_HACCP("Floor HACCP"),
	ALL("All");
	
	
	private String category;

	private ChecklistCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
}
