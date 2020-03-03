package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

	@FXML
	public void addEmployee() {
		loadView(Views.ADD_EMPLOYEE);
	}
	
	@FXML
	public void removeEmployee() {
		loadView(Views.REMOVE_EMPLOYEE);
	}
	
	@FXML
	public void searchEmployee() {
		loadView(Views.SEARCH_EMPLOYEE);
	}
	
	@FXML
	public void updateEmployee() {
		loadView(Views.UPDATE_EMPLOYEE);
	}
	
	@FXML
	public void changePassword() {
		loadView(Views.CHANGE_PASSWORD);
	}
	
	@FXML
	public void openAddTaskWindow() {
		loadView(Views.ADD_TASK);
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
