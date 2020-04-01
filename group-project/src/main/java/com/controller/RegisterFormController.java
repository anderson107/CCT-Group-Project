package com.controller;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.Validation;
import com.saturn.model.employee.Employee;

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
	private Label firstNameLB;

	@FXML
	private Label lastNameLB;

	@FXML
	private Label emailLB;

	@FXML
	private Label dobLB;

	@FXML
	private Label mobileLB;

	@FXML
	private Label addressLB;

	@FXML
	private Label cityLB;

	@FXML
	private void backToMenu() {
		Stage stage = (Stage) backToMenuButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void saveEmployeeDatabase() {

		boolean firstName = Validation.isTextFieldValid(firstNameTF, firstNameLB, "Required");
		boolean lastName = Validation.isTextFieldValid(lastNameTF, lastNameLB, "Required");
		boolean email = Validation.isEmailValid(emailTF, emailLB, "Required");
		boolean dob1 = Validation.isDateEmpty(dob, dobLB, "Required");
		boolean mobile = Validation.isPhoneValid(mobileTF, mobileLB, "Required");
		boolean address = Validation.isTextFieldEmpty(addressTF, addressLB, "Required");
		boolean city = Validation.isTextFieldValid(cityTF, cityLB, "Required");

		if (firstName && lastName && email && dob1 && mobile && address && city) {
			DatabaseConnection.add(new Employee(firstNameTF.getText(), lastNameTF.getText(), emailTF.getText(),
					dob.getValue(), mobileTF.getText(), telephoneTF.getText(), addressTF.getText(), cityTF.getText()));
			Stage stage = (Stage) backToMenuButton.getScene().getWindow();
			stage.close();
		}

	}
}
