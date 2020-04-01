package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.Validation;
import com.saturn.model.employee.Employee;
import com.saturn.model.training.EmployeeHSE;
import com.saturn.model.training.EmployeeSeaChange;
import com.saturn.model.training.EmployeeVirtualAcademy;
import com.saturn.model.training.HSETraining;
import com.saturn.model.training.SeaChangeTraining;
import com.saturn.model.training.TrainingFactory;
import com.saturn.model.training.VirtualAcademyTraining;

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
		   list.addAll("SeaChange", "Virtual Academy", "HSE");
		  //populate the Choice box;  
		   trainingTypeChoiceBox.setItems(list);
		
	}
	
	@FXML
	private void addTraining() {
		
		boolean text = Validation.isTextAreaEmpty(textArea, textLabel, "Required");
		boolean choicebox= Validation.isChoiceBoxSelected(trainingTypeChoiceBox, choiceboxLabel, "Select training");
		
		if(text&&choicebox) {
			
			DatabaseConnection.add(TrainingFactory.create(textArea.getText(), trainingTypeChoiceBox.getValue()));
			
			}
			Stage stage = (Stage) textArea.getScene().getWindow();
			stage.close();
		}
		
	}

