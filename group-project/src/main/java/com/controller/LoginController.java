package com.controller;


import com.saturn.model.Administrator;
import com.saturn.model.Validation;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController {

	@FXML
	private Button signInButton;
	
	@FXML
	private TextField emailTextField;
	
	@FXML
	private TextField passwordTextField;
	
	@FXML
	private Label emailLabel;
	
	@FXML
	private Label passwordLabel;
	
	@FXML
	private Label invalid;
	
	@FXML
	private void openMenu() {
		
		boolean email = Validation.isEmailValid(emailTextField, emailLabel, "Invalid Email");
		boolean password = Validation.isTextFieldEmpty(passwordTextField, passwordLabel, "Invalid password");
		
		if(email && password) {
			
			Dao dao = new Dao();
			Administrator adm = dao.getAdministrator(1);
			
			if(emailTextField.getText().matches(adm.getEmail()) && passwordTextField.getText().matches(adm.getPassword())) {
				
				Stage stage = (Stage) signInButton.getScene().getWindow();
				stage.close();
			}else {
				invalid.setText("Invalid email or password");
				return;
			}
		}
		
	}
}
