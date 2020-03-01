package com.controller;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class UpdateController implements Initializable  {

	//********** private fields ****************
	@FXML
	private javafx.scene.control.ChoiceBox<String>choiceBox;
	@FXML
	private javafx.scene.control.Button backUpdateButton;
	
	@FXML
	private void closeUpdateWindow() {
        Stage stage =(Stage) backUpdateButton.getScene().getWindow();
		stage.close();
	}
	
	// Add options to the choice box
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList();
		   list.addAll("First Name", "Last Name","Address","Email","Mobile","Telephone","City/County","Position");
		  //populate the Choice box;  
		  choiceBox.setItems(list);
	}
	
}
