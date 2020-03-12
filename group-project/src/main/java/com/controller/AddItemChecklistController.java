package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AddItemChecklistController implements Initializable{
	
	//*********** private fields *************
	@FXML
	private javafx.scene.control.ChoiceBox<String>selectChecklist;

	@FXML
	private javafx.scene.control.ChoiceBox<String>selectFrequency;
	
	@FXML
	private javafx.scene.control.Button addItemBackButton;
	
	//********** private and public methods **********************
	
	// it closes add task window when back button is clicked
	@FXML
	private void closeAddTaskWindow() {
        Stage stage =(Stage) addItemBackButton.getScene().getWindow();
		stage.close();
	}

	// it fills up the choice boxes with options 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList();
		   list.addAll("Health and Safety", "Fire Warden", "Task");
		  //populate the Choice box;  
		  selectChecklist.setItems(list);
		  
		  ObservableList<String> list1 = FXCollections.observableArrayList();
		   list1.addAll("Once","Daily", "Weekly","Biweekly","Monthly");
		  //populate the Choice box;  
		  selectFrequency.setItems(list1);
		
	}
}
