package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.HSETraining;
import com.saturn.model.SeaChangeTraining;
import com.saturn.model.TrainingSuperClass;
import com.saturn.model.Validation;
import com.saturn.model.VirtualAcademyTraining;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class UpdateTrainingController implements Initializable {

	@FXML
	private Button backButton;

	@FXML
	private ChoiceBox<String> trainingTypeChoiceBox;

	@FXML
	private TextArea textArea;

	private TrainingSuperClass training = TrainingAdministratorController.selected.get(0);

	@FXML
	private Label textAreaLabel;

	@FXML
	private Label categoryLabel;

	private String className;

	private int trainingID;

	@FXML
	private void closeUpdateTrainingWindow() {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("SeaChange", "Virtual Academy", "HSE");
		// populate the Choice box;
		trainingTypeChoiceBox.setItems(list);
		trainingTypeChoiceBox.setValue(training.getClassName());
		textArea.setText(training.getTraining());
		className = training.getClass().getSimpleName();
		System.out.println(className);
		trainingID = training.getId();
		TrainingAdministratorController.selected.remove(training);
	}

	@FXML
	private void updateTrainingDatabase() {

		boolean textAreaField = Validation.isTextAreaEmpty(textArea, textAreaLabel, "Required");
		boolean choicebox = Validation.isChoiceBoxSelected(trainingTypeChoiceBox, categoryLabel,
				"Select Training Type");

		if (textAreaField && choicebox) {

			if (className.matches("HSETraining")) {
				DatabaseConnection.updateTraining(HSETraining.class, trainingID, textArea.getText(),
						trainingTypeChoiceBox.getValue());
			}else if(className.matches("VirtualAcademyTraining")) {
				DatabaseConnection.updateTraining(VirtualAcademyTraining.class, trainingID, textArea.getText(),
						trainingTypeChoiceBox.getValue());
			}else if(className.matches("SeaChangeTraining")) {
				DatabaseConnection.updateTraining(SeaChangeTraining.class, trainingID, textArea.getText(),
						trainingTypeChoiceBox.getValue());
			}
			
			Stage stage = (Stage) textArea.getScene().getWindow();
			stage.close();
		}
	}

}
