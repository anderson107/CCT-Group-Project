package com.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SearchTaskController {

	@FXML
	private javafx.scene.control.Button searchTaskBackButton;
	
	@FXML
	private void closeSearchTaskWindow() {
		Stage stage = (Stage) searchTaskBackButton.getScene().getWindow();
		stage.close();
	}
}
