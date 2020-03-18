package com.controller;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegisterFormController {

	@FXML
	private TextField firstNameTF;

	@FXML
	private TextField lastNameTF;

	@FXML
	private TextField emailTF;

	@FXML
	private DatePicker dob;

	@FXML
	private TextField mobileTF;

	@FXML
	private TextField telephoneTF;

	@FXML
	private TextField addressTF;

	@FXML
	private TextField cityTF;

	@FXML
	private Button backToMenuButton;

	@FXML
	private void backToMenu() {
		Stage stage = (Stage) backToMenuButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void saveEmployeeDatabase() {

		DatabaseConnection.add(new Employee(firstNameTF.getText(), lastNameTF.getText(), emailTF.getText(),
				dob.getValue(), mobileTF.getText(), telephoneTF.getText(), addressTF.getText(), cityTF.getText()));

		//Stage stage = (Stage) backToMenuButton.getScene().getWindow();
		//stage.close();
	}
}
