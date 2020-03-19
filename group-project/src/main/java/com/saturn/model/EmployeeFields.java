package com.saturn.model;

public enum EmployeeFields {
	
	FIRST_NAME("First Name"),
	LAST_NAME("Last Name"),
	ADDRESS("Address"),
	EMAIL("Email"),
	MOBILE("Mobile"),
	TELEPHONE("Telephone"),
	CITY("City/County");
	
	private String field;

	private EmployeeFields(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}
}
