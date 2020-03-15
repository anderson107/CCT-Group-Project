package com.saturn.model;

public enum ChecklistCategory {

	FIRE_WARDEN("Fire Warden"),
	HEALTH_SAFETY("Health and Safety"),
	TASK("Task");
	
	
	private String category;

	private ChecklistCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
}
