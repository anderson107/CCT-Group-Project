package com.controller;

import com.saturn.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {

	public MainMenuController() {
		
	}
	
	// it opens the add employee window
	@FXML
	private void addEmployee() {
		loadView(Views.ADD_EMPLOYEE,"ADD EMPLOYEE");
	}
	
	// it opens the update employee window
	@FXML
	private void updateEmployee() {
		loadView(Views.UPDATE_EMPLOYEE,"UPDATE EMPLOYEE");
	}
	
	// it opens the reset password window
	@FXML
	private void changePassword() {
		loadView(Views.CHANGE_PASSWORD, "CHANGE PASSWORD");
	}
	
	// it opens the add item checklist window
	@FXML
	private void openAddItemChecklistWindow() {
		loadView(Views.ADD_ITEM_CHECKLIST, "CHECKLIST");
	}
	
	// it opens the employee training window
	@FXML
	private void openEmployeeTrainingWindow() {
		loadView(Views.EMPLOYEE_TRAINING, "TRAININGS");
	}
	
	// it opens the update item checklist window
	@FXML
	private void openUpdateItemChecklistWindow() {
		loadView(Views.UPDATE_ITEM_CHECKLIST, "UPDATE ITEM");
	}
	
	// it opens the report task window
	@FXML
	private void openReportTasksWindow() {
		loadView(Views.TASK_REPORT, "REPORT WINDOW");
	}
	
	// it opens the add training window
	@FXML
	private void openAddTrainingWindow() {
		loadView(Views.ADD_TRAINING,"ADD TRAINING");
	}
	
	// it opens the training administrator window
	@FXML
	private void openTrainingAdministrator() {
		loadView(Views.TRAINING_ADMINISTRATOR, "TRAINING ADM");
	}
	
	// it opens the task administrator window
	@FXML
	private void openTaskAdmistrator() {
		loadView(Views.CHECKLIST_MANAGER, "CHECKLIST MANAGER");
		
	}
	
	// it opens the employee manager window
	@FXML
	private void openEmployeeAdm() {
		loadView(Views.EMPLOYEE_ADM, "EMPLOYEE MANAGER");
	}
	
	// it opens the add task window
	@FXML
	private void openAddTask() {
		loadView(Views.ADD_TASK, "ADD TASK");
	}
	
	// it opens the task manager window
	@FXML
	private void openTaskTable() {
		loadView(Views.TASK_TABLE, "TASK MANAGER");
	}
	
	// it opens the add maintenance window
	@FXML
	private void openAddMaintenance() {
		loadView(Views.ADD_CONTRACTOR, "ADD CONTRACTOR");
	}
	
	// it opens the maintenance manager window
	@FXML
	private void openMaintenanceTable() {
		loadView(Views.MAINTENANCE_TABLE, "CONTRACTOR MANAGER");
	}
	
	// it opens the report option short cuts window
	@FXML
	private void openReportOptions() {
		loadView(Views.REPORT_OPTION, "");
	}
	
	// it opens the checklist report window
	@FXML
	private void openChecklistReport() {
		loadView(Views.CHECKLIST_REPORT, "CHECKLIST REPORT GENERATOR");
	}
	
	// it opens the maintenance report window
	@FXML
	private void openMaintenanceReport(){
		loadView(Views.MAINTENANCE_REPORT, "MAINTENANCE REPORT GENERATOR");
	}
	
	// it opens the training report window
	@FXML
	private void openTrainingReport(){
		loadView(Views.TRAINING_REPORT, "TRAINING REPORT GENERATOR");
	}
	
	// it opens the checklist manager window
	@FXML
	private void openChecklistAdm() {
		loadView(Views.TASK_ADMINISTRATOR, "CHECKLIST ITEMS MANAGER");
	}
	
	// method to upload the fxml files
	public void loadView(String view, String windowTitle) {
		try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
		        Parent root1 = (Parent) fxmlLoader.load();
		        Stage stage = new Stage();
		        stage.setScene(new Scene(root1));  
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.initOwner(App.stage);
		        stage.setTitle(windowTitle);
		        stage.setResizable(false);
		        stage.show();
		        
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
