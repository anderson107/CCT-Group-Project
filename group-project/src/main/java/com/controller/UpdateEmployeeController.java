package com.controller;

import java.util.ResourceBundle;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.Validation;
import com.saturn.model.employee.Employee;
import com.saturn.model.employee.EmployeeFields;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class UpdateEmployeeController implements Initializable {

	// ********** private fields ****************
	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private Button backUpdateButton;

	@FXML
	private TextArea textArea;

	@FXML
	private Label updateLabel;

	@FXML
	private Label newDataLabel;

	@FXML
	private TextField textField;

	private int employeeId = 0;
	
	@FXML
	protected Button updateEmployee;
	
	@FXML
	private void closeUpdateWindow() {
		Stage stage = (Stage) backUpdateButton.getScene().getWindow();
		stage.close();
	}

	// Add options to the choice box
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		Employee emp = EmployeeAdministratorController.selected.get(0);

		textArea.setText("Name: " + emp.getFirstName() + " " + emp.getLastName() + "\nEmail: " + emp.getEmail()
				+ "\nAddress: " + emp.getAddress() + "\nCity: " + emp.getCity() + "\nMobile: " + emp.getMobile()
				+ "\nTelephone: " + emp.getTelephone());

		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll(EmployeeFields.FIRST_NAME.getField(), EmployeeFields.LAST_NAME.getField(),
				EmployeeFields.ADDRESS.getField(), EmployeeFields.EMAIL.getField(), EmployeeFields.MOBILE.getField(),
				EmployeeFields.TELEPHONE.getField(), EmployeeFields.CITY.getField());
		// populate the Choice box;
		choiceBox.setItems(list);
		
		employeeId = emp.getId();
		EmployeeAdministratorController.selected.remove(emp);
	}

	// it updates the employee information
	@FXML
	private void updateEmployee() {
		
		boolean choicebox = Validation.isChoiceBoxSelected(choiceBox, updateLabel, "Select an option");
		boolean newData = Validation.isTextFieldEmpty(textField, newDataLabel, "Enter new data");
		
		if(choicebox&&newData) {
			String choice = choiceBox.getValue();
			
			switch(choice) {
			case "First Name":
			case "Last Name":
				boolean validation1 = Validation.isTextFieldValid(textField, newDataLabel, "Enter a valid name");
				if(validation1) {
					DatabaseConnection.updateEmployee(employeeId, choice, textField.getText());
					break;
				}else {
					return;
				}
			case "Email":
				boolean validation2 = Validation.isEmailValid(textField, newDataLabel, "Enter a valid email");
				if(validation2) {
					DatabaseConnection.updateEmployee(employeeId, choice, textField.getText());
					break;
				}else {
					return;
				}
			case "Mobile":
			case "Telephone":
				boolean validation3 = Validation.isPhoneValid(textField, newDataLabel, "Enter a valid number");
				if(validation3) {
					DatabaseConnection.updateEmployee(employeeId, choice, textField.getText());
					break;
				}else {
					return;
				}
			case "Address":
			case "City/County":
				boolean validation4 = Validation.isTextFieldEmpty(textField, newDataLabel, "Required");
				if(validation4) {
					DatabaseConnection.updateEmployee(employeeId, choice, textField.getText());
					break;
				}else {
					return;
				}
				
			}
			
			
			Stage stage = (Stage) newDataLabel.getScene().getWindow();
			stage.close();
		}
	}
}
