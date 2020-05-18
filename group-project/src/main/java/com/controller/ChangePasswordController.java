package com.controller;

import com.saturn.model.Administrator;
import com.saturn.model.Validation;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ChangePasswordController {

	@FXML
	private TextField loginTF;
	
	@FXML
	private TextField passwordTF;
	
	@FXML
	private TextField newPasswordTF;
	
	@FXML
	private Label loginLabel;
	
	@FXML
	private Label passwordLabel;
	
	@FXML
	private Label newPasswordLabel;
	
	@FXML
	private Label warningLabel;
	
	@FXML
	private void resetPassword() {
		
		Dao dao = new Dao();
		
		String password = passwordTF.getText();
		String login = loginTF.getText();
		String newPassword = newPasswordTF.getText();
		
		boolean checkPassword = Validation.isTextFieldEmpty(passwordTF, passwordLabel, "Enter a valid password");
		boolean checkLogin = Validation.isTextFieldEmpty(loginTF, loginLabel, "Enter a valid login");
		boolean checkNewPassword = Validation.isTextFieldEmpty(newPasswordTF, newPasswordLabel, "Enter a valid password");
		
		if(checkPassword&&checkLogin&&checkNewPassword) {
			
			Administrator adm = dao.getAdministrator(1);
			
			if(adm.getLogin().matches(login) && adm.getPassword().matches(password)) {
				
				dao.changePassword(newPassword);
				
				Stage stage = (Stage) loginTF.getScene().getWindow();
				stage.close();
				
			}else {
				warningLabel.setText("Enter a valid login and password");
			}
			
		}
	}
}
