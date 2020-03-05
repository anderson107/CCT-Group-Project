package com.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class UpdateTaskController {

	@FXML
	private javafx.scene.control.Button updateTaskBackButton;
	
	@FXML
	private void closeUpdateTaskWindow() {
		Stage stage = (Stage) updateTaskBackButton.getScene().getWindow();
		stage.close();
	}
}
