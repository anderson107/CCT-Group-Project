package com.controller;

import com.saturn.Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {

	@FXML
	private Button taskAdministratorButton;
	
	@FXML
	private Button updateTaskAdministratorButton;
	
	public MainMenuController() {
	}
	
	@FXML
	private void addEmployee() {
		loadView(Views.ADD_EMPLOYEE,"ADD EMPLOYEE");
	}
	
	@FXML
	private void updateEmployee() {
		loadView(Views.UPDATE_EMPLOYEE,"UPDATE EMPLOYEE");
	}
	
	@FXML
	private void changePassword() {
		loadView(Views.CHANGE_PASSWORD, "CHANGE PASSWORD");
	}
	
	@FXML
	private void openAddItemChecklistWindow() {
		loadView(Views.ADD_ITEM_CHECKLIST, "CHECKLIST");
	}
	
	@FXML
	private void openEmployeeTrainingWindow() {
		loadView(Views.EMPLOYEE_TRAINING, "TRAININGS");
	}
	
	@FXML
	private void openUpdateItemChecklistWindow() {
		loadView(Views.UPDATE_ITEM_CHECKLIST, "UPDATE ITEM");
	}
	
	@FXML
	private void openReportTasksWindow() {
		loadView(Views.TASK_REPORT, "REPORT WINDOW");
	}
	
	@FXML
	private void openAddTrainingWindow() {
		loadView(Views.ADD_TRAINING,"ADD TRAINING");
	}
	
	@FXML
	private void openTrainingAdministrator() {
		loadView(Views.TRAINING_ADMINISTRATOR, "TRAINING ADM");
	}
	
	@FXML
	private void openTaskAdmistrator() {
		loadView(Views.CHECKLIST_MANAGER, "CHECKLIST MANAGER");
		
	}
	
	@FXML
	private void openEmployeeAdm() {
		loadView(Views.EMPLOYEE_ADM, "EMPLOYEE MANAGER");
	}
	
	@FXML
	private void openAddTask() {
		loadView(Views.ADD_TASK, "ADD TASK");
	}
	
	@FXML
	private void openTaskTable() {
		loadView(Views.TASK_TABLE, "TASK MANAGER");
	}
	
	@FXML
	private void openAddMaintenance() {
		loadView(Views.ADD_CONTRACTOR, "ADD CONTRACTOR");
	}
	
	@FXML
	private void openMaintenanceTable() {
		loadView(Views.MAINTENANCE_TABLE, "CONTRACTOR MANAGER");
	}
	
	@FXML
	private void openReportOptions() {
		loadView(Views.REPORT_OPTION, "");
	}
	
	@FXML
	private void openChecklistReport() {
		loadView(Views.CHECKLIST_REPORT, "CHECKLIST REPORT GENERATOR");
	}
	
	@FXML
	private void openMaintenanceReport(){
		loadView(Views.MAINTENANCE_REPORT, "MAINTENANCE REPORT GENERATOR");
	}
	
	@FXML
	private void openTrainingReport(){
		loadView(Views.TRAINING_REPORT, "TRAINING REPORT GENERATOR");
	}
	
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
		        stage.initOwner(Main.stage);
		        stage.setTitle(windowTitle);
		        stage.setResizable(false);
		        stage.show();
		        
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
