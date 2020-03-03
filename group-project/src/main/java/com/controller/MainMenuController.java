package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

	@FXML
	private void addEmployee() {
		loadView(Views.ADD_EMPLOYEE);
	}
	
	@FXML
	private void removeEmployee() {
		loadView(Views.REMOVE_EMPLOYEE);
	}
	
	@FXML
	private void searchEmployee() {
		loadView(Views.SEARCH_EMPLOYEE);
	}
	
	@FXML
	private void updateEmployee() {
		loadView(Views.UPDATE_EMPLOYEE);
	}
	
	@FXML
	private void changePassword() {
		loadView(Views.CHANGE_PASSWORD);
	}
	
	@FXML
	private void openAddTaskWindow() {
		loadView(Views.ADD_TASK);
	}
	
	@FXML
	private void openRemoveTaskWindow() {
		loadView(Views.REMOVE_TASK);
	}
	
	@FXML
	private void openSearchTaskWindow() {
		loadView(Views.SEARCH_TASK);
	}
	
	@FXML
	private void openUpdateTaskWindow() {
		loadView(Views.UPDATE_TASK);
	}
	
	// method to upload the fxml files
	private void loadView(String view) {
		try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
		        Parent root1 = (Parent) fxmlLoader.load();
		        Stage stage = new Stage();
		        stage.setScene(new Scene(root1));  
		        stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
