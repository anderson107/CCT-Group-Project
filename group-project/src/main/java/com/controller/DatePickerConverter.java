package com.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import javafx.util.StringConverter;

public class DatePickerConverter extends StringConverter {

	private String pattern = "dd-MM-yyyy";

	private DateTimeFormatter formatter;

	public DatePickerConverter() {
		formatter = DateTimeFormatter.ofPattern(pattern);
	}

	public DatePickerConverter(String pattern) {
		this.pattern = pattern;
		formatter = DateTimeFormatter.ofPattern(pattern);
	}

	// String to date
	public LocalDate fromString(String text) {

		LocalDate update = LocalDate.parse(text, formatter);

		return update;
	}

	// date to String
	public String toString(Object date) {
		String text = null;
		if (date != null) {
			text = formatter.format((TemporalAccessor) date);
		}
		return text;
	}
}
