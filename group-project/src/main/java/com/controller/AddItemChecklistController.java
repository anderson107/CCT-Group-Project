package com.controller;

import java.net.URL;
import java.util.ResourceBundle;


import com.saturn.dao.DatabaseConnection;
import com.saturn.model.Validation;
import com.saturn.model.checklists.ChecklistCategory;
import com.saturn.model.checklists.ChecklistFactory;
import com.saturn.model.checklists.FireWarden;
import com.saturn.model.checklists.Frequency;
import com.saturn.model.checklists.HealthSafetyChecklist;
import com.saturn.model.checklists.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class AddItemChecklistController implements Initializable {

	// *********** private fields *************
	@FXML
	private ChoiceBox<String> selectChecklist;

	@FXML
	private ChoiceBox<String> selectFrequency;

	@FXML
	private Button addItemBackButton;

	@FXML
	private TextArea addItemTextfield;
	
	@FXML
	private Button addItemChecklist;
	
	@FXML
	private Label checklistLabel;
	
	@FXML
	private Label frequencyLabel;
	
	@FXML
	private Label textAreaLabel;

	// ********** private and public methods **********************

	// it closes add task window when back button is clicked
	@FXML
	private void closeAddTaskWindow() {
		Stage stage = (Stage) addItemBackButton.getScene().getWindow();
		stage.close();
	}

	// it fills up the choice boxes with options
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll(ChecklistCategory.HEALTH_SAFETY.getCategory(), ChecklistCategory.FIRE_WARDEN.getCategory(),
				ChecklistCategory.TASK.getCategory());
		// populate the Choice box;
		selectChecklist.setItems(list);

		ObservableList<String> list1 = FXCollections.observableArrayList();
		list1.addAll(Frequency.ONCE.getFrequency(), Frequency.DAILY.getFrequency(), Frequency.WEEKLY.getFrequency(),
				Frequency.BIWEEKLY.getFrequency(), Frequency.SEMIANNUAL.getFrequency(),
				Frequency.YEARLY.getFrequency());
		// populate the Choice box;
		selectFrequency.setItems(list1);

	}

	// It adds items to checklist fire warden, health and safety and task
	@FXML
	public void addItem(ActionEvent actionEvent) {
		
		Boolean choiceboxCategoryValidation = Validation.isChoiceBoxSelected(selectChecklist, checklistLabel, "Select a category");
		Boolean choiceboxFrequencyValidation = Validation.isChoiceBoxSelected(selectFrequency, frequencyLabel, "Select frequency");
		Boolean textAreaValidation = Validation.isTextAreaEmpty(addItemTextfield, textAreaLabel, "Enter item description");
		
		if(choiceboxCategoryValidation==true && choiceboxFrequencyValidation==true && textAreaValidation==true) {
			
			DatabaseConnection.add(ChecklistFactory.create(addItemTextfield.getText(), "Pending", selectFrequency.getValue(), selectChecklist.getValue()));
			
			Stage stage = (Stage) addItemTextfield.getScene().getWindow();
			stage.close();
		}
			
		
	}
}
