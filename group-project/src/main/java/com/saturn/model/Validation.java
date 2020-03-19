package com.saturn.model;

import javax.swing.JOptionPane;

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

	public static boolean isDateEmpty(DatePicker datepicker, Label label, String text) {
		boolean valid = true;

		if (datepicker.getValue() == null) {
			valid = false;
			label.setText(text);
			datepicker.requestFocus();
		} else {
			valid = true;
			label.setText("");
		}
		return valid;
	}

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

	public static boolean isEmailValid(TextField tf, Label label, String text) {
		boolean valid = true;

		if (tf.getText().isEmpty()) {
			valid = false;
			label.setText(text);
			tf.requestFocus();
		} else if (!tf.getText().trim().matches(
				"^(.+)@(.+)$")) {
			JOptionPane.showMessageDialog(null, "Email be a valid email");
		}

		else {
			valid = true;
			label.setText("");
		}
		return valid;
	}

	public static boolean isPhoneValid(TextField tf, Label label, String text) {
		boolean valid = true;

		if (!tf.getText().trim().matches("[0-9]+")) {
			valid = false;
			label.setText(text);
			tf.requestFocus();
		} else {
			valid = true;
			label.setText("");
		}

		return valid;
	}

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

}
