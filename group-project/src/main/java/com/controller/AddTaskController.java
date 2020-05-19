package com.controller;

import com.saturn.model.Validation;
import com.saturn.model.task.Task;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddTaskController {
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label textAreaLabel;
	
	@FXML
	private Label dateLabel;

	// it closes the add task window
	@FXML
	private void closeWindow() {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}
	
	// it validates the user input and add it to the database
	@FXML
	private void addItem(){
		Dao dao = new Dao();
		boolean text = Validation.isTextAreaEmpty(textArea, textAreaLabel, "Required");
		boolean date = Validation.isDateEmpty(datePicker, dateLabel, "Required");
		
		if(text && date) {
			Task task = new Task(textArea.getText());
			task.setDate(datePicker.getValue());
			
			dao.add(task);
			
			closeWindow();
		}
	}
	
}
