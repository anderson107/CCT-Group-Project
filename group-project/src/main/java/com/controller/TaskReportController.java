package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class TaskReportController implements Initializable {

	@FXML
	private javafx.scene.control.ChoiceBox<String> frequencyTaskReportChoiceBox;
	
	@FXML
	private javafx.scene.control.ChoiceBox<String> statusTaskReportChoiceBox;
	
	@FXML
	private javafx.scene.control.ChoiceBox<String>selectTaskReportChoiceBox;
	
	@FXML
	private javafx.scene.control.Button taskReportBackButton;
	
	@FXML
	private void closeTaskReportWindow() {
		Stage stage = (Stage) taskReportBackButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void printReport() {
	
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<String> list = FXCollections.observableArrayList();
		   list.addAll("Health and Safety", "Fire Warden", "All Tasks");
		  //populate the Choice box;  
		   selectTaskReportChoiceBox.setItems(list);
		  
		  ObservableList<String> list1 = FXCollections.observableArrayList();
		   list1.addAll("Daily", "Weekly","Biweekly","Monthly");
		  //populate the Choice box;  
		   frequencyTaskReportChoiceBox.setItems(list1);
		   
		   ObservableList<String> list2 = FXCollections.observableArrayList();
		   list2.addAll("Today", "Pending","All Tasks");
		  //populate the Choice box;  
		   statusTaskReportChoiceBox.setItems(list2);
		
	}

}
