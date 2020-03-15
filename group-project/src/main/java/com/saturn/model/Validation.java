package com.saturn.model;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Validation {
	
	// it validates text area fields
	public static boolean isTextAreaEmpty(TextArea ta, Label label, String text) {
		boolean isValid = true;
		
		if(ta.getText().isEmpty()) {
			isValid = false;
			label.setText(text);
			ta.requestFocus();
		}else {
			isValid = true;
			label.setText("");
		}
		
		return isValid;
	}
	
	// it validates Choice box fields
	public static boolean isChoiceBoxSelected(ChoiceBox<String> choicebox, Label label, String text) {
		boolean isValid = true;
		
		if(choicebox.getValue()==null) {
			isValid = false;
			label.setText(text);
			choicebox.requestFocus();
		}else {
			isValid = true;
			label.setText("");
		}
		return isValid;
	}

}
