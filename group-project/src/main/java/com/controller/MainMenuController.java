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
	private void openAddItemChecklistWindow() {
		loadView(Views.ADD_ITEM_CHECKLIST);
	}
	
	@FXML
	private void openRemoveItemChecklistWindow() {
		loadView(Views.REMOVE_ITEM_CHECKLIST);
	}
	
	@FXML
	private void openSearchItemChecklistWindow() {
		loadView(Views.SEARCH_ITEM_CHECKLIST);
	}
	
	@FXML
	private void openUpdateItemChecklistWindow() {
		loadView(Views.UPDATE_ITEM_CHECKLIST);
	}
	
	@FXML
	private void openReportTasksWindow() {
		loadView(Views.TASK_REPORT);
	}
	
	@FXML
	private void openAddTrainingWindow() {
		loadView(Views.ADD_TRAINING);
	}
	
	@FXML
	private void openRemoveTrainingWindow() {
		loadView(Views.REMOVE_TRAINING);
	}
	
	@FXML
	private void openSearchTrainingWindow() {
		loadView(Views.SEARCH_TRAINING);
	}
	
	@FXML
	private void openUpdateTrainingWindow() {
		loadView(Views.UPDATE_TRAINING);
	}
	
	@FXML
	private void openTaskAdmistrator() {
		loadView(Views.TASK_ADMINISTRATOR);
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
