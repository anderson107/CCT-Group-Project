package com.saturn.model;

public enum Frequency {
	
	ONCE("Once"),
	DAILY("Daily"),
	WEEKLY("Weekly"),
	BIWEEKLY("Biweekly"),
	MONTHLY("Monthly"),
	SEMIANNUAL("Semiannual"),
	YEARLY("Yearly");
	
	private String frequency;

	private Frequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFrequency() {
		return frequency;
	}
}
