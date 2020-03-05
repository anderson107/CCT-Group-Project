package com.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AddTaskController {

	@FXML
	private javafx.scene.control.Button addTaskBackButton;
	
	@FXML
	private void closeAddTaskWindow() {
		Stage stage = (Stage) addTaskBackButton.getScene().getWindow();
		stage.close();
	}
}
