package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class UpdateTrainingController implements Initializable{

	@FXML
	private javafx.scene.control.Button updateTrainingBackButton;
	
	@FXML
	private javafx.scene.control.ChoiceBox<String> trainingTypeChoiceBox;
	
	@FXML
	private void closeUpdateTrainingWindow() {
		Stage stage = (Stage) updateTrainingBackButton.getScene().getWindow();
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
