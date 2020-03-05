package com.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RemoveTrainingController {

	@FXML
	private javafx.scene.control.Button removeTrainingBackButton;
	
	@FXML
	private void closeRemoveTrainingWindow() {
		Stage stage = (Stage) removeTrainingBackButton.getScene().getWindow();
		stage.close();
	}
}
