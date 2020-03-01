package com.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SearchEmployeeController {
	
	@FXML
	private javafx.scene.control.Button searchBackButton;
	
	@FXML
	public void searchCloseWindows() {
		// get stage
		Stage stage = (Stage) searchBackButton.getScene().getWindow();
	    // close stage
	    stage.close();
	}

}
