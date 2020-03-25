package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.HSETraining;
import com.saturn.model.SeaChangeTraining;
import com.saturn.model.Validation;
import com.saturn.model.VirtualAcademyTraining;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class AddTrainingController implements Initializable{

	@FXML
	private ChoiceBox<String>trainingTypeChoiceBox;
	
	@FXML
	private Button addTrainingBackButton;
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private Label textLabel;
	
	@FXML
	private Label choiceboxLabel;
	
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
	
	@FXML
	private void addTraining() {
		
		boolean text = Validation.isTextAreaEmpty(textArea, textLabel, "Required");
		boolean choicebox= Validation.isChoiceBoxSelected(trainingTypeChoiceBox, choiceboxLabel, "Select training");
		
		if(text&&choicebox) {
			switch(trainingTypeChoiceBox.getValue()){
			case "SeaChange":
				DatabaseConnection.add(new SeaChangeTraining(textArea.getText()));
				break;
			case "VirtualAcademy":
				DatabaseConnection.add(new VirtualAcademyTraining(textArea.getText()));
				break;
			case "HSE":
				DatabaseConnection.add(new HSETraining(textArea.getText()));
				break;
			}
			//DatabaseConnection.addTraining(1, 1);
			Stage stage = (Stage) textArea.getScene().getWindow();
			stage.close();
		}
	}
}
