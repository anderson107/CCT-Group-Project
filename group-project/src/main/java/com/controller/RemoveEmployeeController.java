package com.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RemoveEmployeeController {
	
	@FXML
	private javafx.scene.control.Button backMenuRemoveEmployeeButton;

	@FXML
	public void closeRemoveEmployeeWindow(){
		
		Stage stage =(Stage) backMenuRemoveEmployeeButton.getScene().getWindow();
		
		stage.close();
	}
}
