package com.controller;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.Validation;
import com.saturn.model.maintenance.Maintenance;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddMaintenanceController {

	@FXML
	private Button backButton;
	
	@FXML
	private TextField textField;
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private Label textFieldLabel;
	
	@FXML
	private Label textAreaLabel;
	
	@FXML
	private DatePicker lastDate;
	
	@FXML
	private DatePicker nextDate;
	
	// it closes the contractor window
	@FXML
	private void closeWindow() {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}
	
	// it validates the inputs and add the contractor to the database
	@FXML
	private void addContractor() {
		
		boolean contractor = Validation.isTextFieldEmpty(textField, textFieldLabel, "Required");
		boolean service = Validation.isTextAreaEmpty(textArea, textAreaLabel, "Required");
		
		if(contractor && service) {
			
			Maintenance maintenance = new Maintenance(textField.getText(), textArea.getText());
			maintenance.setLastDate(lastDate.getValue());
			maintenance.setNextDate(nextDate.getValue());
			DatabaseConnection.add(maintenance);
			
			Stage stage = (Stage) textField.getScene().getWindow();
			stage.close();
		}
	}
}
