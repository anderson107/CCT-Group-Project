package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class AddTrainingController implements Initializable{

	@FXML
	private javafx.scene.control.ChoiceBox<String>trainingTypeChoiceBox;
	
	@FXML
	private javafx.scene.control.Button addTrainingBackButton;
	
	@FXML
	private void closeAddTrainingWindow() {
		Stage stage = (Stage) addTrainingBackButton.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList();
		   list.addAll("SeaChange", "VirtualAcademy", "HSE");
		  //populate the Choice box;  
		   trainingTypeChoiceBox.setItems(list);
		
	}
	
	
}
