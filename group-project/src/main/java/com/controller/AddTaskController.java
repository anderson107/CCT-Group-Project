package com.controller;

import com.saturn.dao.DatabaseConnection;
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

	@FXML
	private void closeWindow() {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void addItem(){
		boolean text = Validation.isTextAreaEmpty(textArea, textAreaLabel, "Required");
		boolean date = Validation.isDateEmpty(datePicker, dateLabel, "Required");
		
		if(text && date) {
			Task task = new Task(textArea.getText());
			task.setDate(datePicker.getValue());
			
			DatabaseConnection.add(task);
			
			closeWindow();
		}
	}
	
}
