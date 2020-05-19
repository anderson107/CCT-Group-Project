package com.saturn.model;


import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Validation {

	// it validates text area fields
	public static boolean isTextAreaEmpty(TextArea ta, Label label, String text) {
		boolean isValid = true;

		if (ta.getText().isEmpty()) {
			isValid = false;
			label.setText(text);
			ta.requestFocus();
		} else {
			isValid = true;
			label.setText("");
		}

		return isValid;
	}

	// it validates Choice box fields
	public static boolean isChoiceBoxSelected(ChoiceBox<String> choicebox, Label label, String text) {
		boolean isValid = true;

		if (choicebox.getValue() == null) {
			isValid = false;
			label.setText(text);
			choicebox.requestFocus();
		} else {
			isValid = true;
			label.setText("");
		}
		return isValid;
	}

	// it validates the date input
	public static boolean isDateEmpty(DatePicker datepicker, Label label, String text) {
		boolean valid = true;

		if (datepicker.getValue() == null) {
			valid = false;
			label.setText(text);
			datepicker.requestFocus();
			return valid;
		} else {
			valid = true;
			label.setText("");
		}
		return valid;
	}

	// it validates if a text field is valid, only English alphabet is allowed
	public static boolean isTextFieldValid(TextField tf, Label label, String text) {
		boolean valid = true;

		if (!tf.getText().trim().matches("[a-zA-Z]+")) {
			valid = false;
			label.setText(text);
			tf.requestFocus();
		} else {
			valid = true;
			label.setText("");
		}

		return valid;
	}

	// it validate the email
	public static boolean isEmailValid(TextField tf, Label label, String text) {
		boolean valid = true;

		if (tf.getText().isEmpty()) {
			valid = false;
			label.setText(text);
			tf.requestFocus();
		} else if (!tf.getText().trim().matches(
				"^(.+)@(.+)$")) {
			label.setText("email@email.com");
		}

		else {
			valid = true;
			label.setText("");
		}
		return valid;
	}

	// it validates the phone number
	public static boolean isPhoneValid(TextField tf, Label label, String text) {
		
		boolean valid = false;
		
		if(tf.getText().matches("[0-9]+")) {
			
			if(tf.getText().length() < 7 || tf.getText().length()>10) {
				valid = false;
				label.setText("Minimum 7 and maximum 10 characters");
				tf.requestFocus();
				return false;
			}
			
			valid = true;
			label.setText("");
			
		}else {
			valid = false;
			label.setText("Required");
		}
		
		return valid;
	}

	// it checks if the text field is empty
	public static boolean isTextFieldEmpty(TextField tf, Label label, String text) {
		boolean valid = true;

		if (tf.getText().isEmpty()) {
			valid = false;
			label.setText(text);
			tf.requestFocus();
		} else {
			valid = true;
			label.setText("");
		}

		return valid;
	}

	// it checks if the input in a valid number
	public static boolean isNumber(TextField tf, Label label, String text) {
		boolean valid = true;

		if (!tf.getText().matches("[0-9]+")) {
			valid = false;
			label.setText(text);
			tf.requestFocus();
		} else {
			valid = true;
			label.setText("");
		}

		return valid;
	}

	
}
