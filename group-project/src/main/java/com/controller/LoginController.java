package com.controller;

import com.saturn.Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button signInButton;
	
	@FXML
	private TextField emailTextField;
	
	@FXML
	private TextField passwordTextField;
	
	private String email = "admin@admin.admin";
	
	private String password = "admin";
	
	
	@FXML
	private void openMenu() {
		
		if(emailTextField.getText().matches(email) && passwordTextField.getText().matches(password)) {
			
			try {
				 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.MAIN_MENU));
			        Parent root1 = (Parent) fxmlLoader.load();
			        Stage stage = new Stage();
			        stage.setScene(new Scene(root1));  
			        stage.initOwner(Main.stage);
			        stage.setMaximized(true);
			        stage.setTitle("HEALTH AND SAFETY APPLICATION");
			        stage.show();
			        
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
