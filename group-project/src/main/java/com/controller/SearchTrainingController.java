package com.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SearchTrainingController {

	@FXML
	private javafx.scene.control.Button searchTrainingBackButton;
	
	@FXML
	private void closeSearchTrainingWindow() {
		Stage stage = (Stage) searchTrainingBackButton.getScene().getWindow();
		stage.close();
	}
}
