package com.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RemoveTaskController {

	@FXML
	private javafx.scene.control.Button removeTaskBackButton;
	
	@FXML
	private void closeRemoveTaskWindow() {
		Stage stage = (Stage) removeTaskBackButton.getScene().getWindow();
		stage.close();
	}
}
