package com.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RemoveEmployeeController {
	
	@FXML
	private javafx.scene.control.Button backMenuRemoveEmployeeButton;

	@FXML
	public void closeRemoveEmployeeWindow(Event e){
		
		Stage stage =(Stage) backMenuRemoveEmployeeButton.getScene().getWindow();
		
		stage.close();
	}
}
