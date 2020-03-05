package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class UpdateItemChecklistController implements Initializable {

	@FXML
	private javafx.scene.control.ChoiceBox<String> updateSelectTypeChoiceBox;
	
	@FXML
	private javafx.scene.control.ChoiceBox<String> updateSelectFrequencyChoiceBox;
	
	@FXML
	private javafx.scene.control.Button updateItemBackButton;
	
	@FXML
	private void closeUpdateTaskWindow() {
		Stage stage = (Stage) updateItemBackButton.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList();
		   list.addAll("Health and Safety", "Fire Warden");
		  //populate the Choice box;  
		   updateSelectTypeChoiceBox.setItems(list);
		  
		  ObservableList<String> list1 = FXCollections.observableArrayList();
		   list1.addAll("Daily", "Weekly","Biweekly","Monthly");
		  //populate the Choice box;  
		   updateSelectFrequencyChoiceBox.setItems(list1);
		
	}
	
}
