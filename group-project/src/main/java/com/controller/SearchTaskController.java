package com.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SearchTaskController {

	@FXML
	private javafx.scene.control.Button searchTaskButton;
	
	@FXML
	private void closeSearchTaskWindow() {
		Stage stage = (Stage) searchTaskButton.getScene().getWindow();
		stage.close();
	}
}
