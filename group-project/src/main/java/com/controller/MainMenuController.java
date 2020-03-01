package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

	@FXML
	public void addEmployee() {
		String view = Views.ADD_EMPLOYEE;
		loadView(view);
	}
	
	@FXML
	public void removeEmployee() {
		String view = Views.REMOVE_EMPLOYEE;
		loadView(view);
	}
	
	@FXML
	public void searchEmployee() {
		String view = Views.SEARCH_EMPLOYEE;
		loadView(view);
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
