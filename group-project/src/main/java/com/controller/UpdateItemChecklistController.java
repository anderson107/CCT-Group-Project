package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.Validation;
import com.saturn.model.checklists.ChecklistCategory;
import com.saturn.model.checklists.ChecklistSuperClass;
import com.saturn.model.checklists.FireWarden;
import com.saturn.model.checklists.Frequency;
import com.saturn.model.checklists.HealthSafetyChecklist;
import com.saturn.model.checklists.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class UpdateItemChecklistController implements Initializable {

	@FXML
	private ChoiceBox<String> updateSelectTypeChoiceBox;

	@FXML
	private ChoiceBox<String> updateSelectFrequencyChoiceBox;

	@FXML
	private ChoiceBox<String> updateStatus;

	@FXML
	private TextArea textAreaUpdate;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Label textAreaLabel;

	@FXML
	private Label selectFrequencyLabel;

	@FXML
	private Label selectCategoryLabel;

	@FXML
	private Label selectStatusLabel;

	@FXML
	private Label selectDateLabel;

	private int itemToBeUpdatedIndex;
	
	private String className;

	@FXML
	private void closeUpdateTaskWindow() {
		// Stage stage = (Stage) updateItemBackButton.getScene().getWindow();
		// stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ChecklistSuperClass updateItem = TaskAdministratorController.selected.get(0);
		// it populates checklist type choice box
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll(ChecklistCategory.HEALTH_SAFETY.getCategory(), ChecklistCategory.FIRE_WARDEN.getCategory(),
				ChecklistCategory.TASK.getCategory());
		updateSelectTypeChoiceBox.setItems(list);

		// it sets value to type choice box
		if (updateItem.getClass().getSimpleName().matches("HealthSafetyChecklist")) {
			updateSelectTypeChoiceBox.setValue(ChecklistCategory.HEALTH_SAFETY.getCategory());
		} else if (updateItem.getClass().getSimpleName().matches("FireWarden")) {
			updateSelectTypeChoiceBox.setValue(ChecklistCategory.FIRE_WARDEN.getCategory());
		} else if (updateItem.getClass().getSimpleName().matches("Task")) {
			updateSelectTypeChoiceBox.setValue(ChecklistCategory.TASK.getCategory());
		}

		// it populates frequency choice box
		ObservableList<String> list1 = FXCollections.observableArrayList();
		list1.addAll(Frequency.ONCE.getFrequency(), Frequency.DAILY.getFrequency(), Frequency.WEEKLY.getFrequency(),
				Frequency.BIWEEKLY.getFrequency(), Frequency.SEMIANNUAL.getFrequency(),
				Frequency.YEARLY.getFrequency());
		updateSelectFrequencyChoiceBox.setItems(list1);
		updateSelectFrequencyChoiceBox.setValue(updateItem.getFrequency());

		// it populates status choice box
		ObservableList<String> list2 = FXCollections.observableArrayList();
		list2.addAll("Pending", "Done");
		updateStatus.setItems(list2);
		updateStatus.setValue(updateItem.getStatus());
		textAreaUpdate.setText(updateItem.getItemDescription());
		itemToBeUpdatedIndex = updateItem.getId();
		className = updateItem.getClass().getSimpleName();
		datePicker.setValue(updateItem.getDueDate());
		TaskAdministratorController.selected.remove(updateItem);
	}

	@FXML
	private void updateItemDatabase() {
		boolean category = Validation.isChoiceBoxSelected(updateSelectTypeChoiceBox, selectCategoryLabel,
				"Select a category");
		boolean frequency = Validation.isChoiceBoxSelected(updateSelectFrequencyChoiceBox, selectFrequencyLabel,
				"Select frequency");
		boolean status = Validation.isChoiceBoxSelected(updateStatus, selectStatusLabel, "Select status");
		boolean itemDescription = Validation.isTextAreaEmpty(textAreaUpdate, textAreaLabel, "Enter new description");
		boolean date = Validation.isDateEmpty(datePicker, selectDateLabel, "Pick a date");

		if (category && frequency && status && date && itemDescription) {
			
			if(className.matches("FireWarden")) {
				DatabaseConnection.updateItemChecklistItem(FireWarden.class, itemToBeUpdatedIndex, textAreaUpdate.getText(),
						updateSelectTypeChoiceBox.getValue(), updateSelectFrequencyChoiceBox.getValue(),
						updateStatus.getValue(), datePicker);
				
			}else if(className.matches("HealthSafetyChecklist")) {
				DatabaseConnection.updateItemChecklistItem(HealthSafetyChecklist.class, itemToBeUpdatedIndex, textAreaUpdate.getText(),
						updateSelectTypeChoiceBox.getValue(), updateSelectFrequencyChoiceBox.getValue(),
						updateStatus.getValue(), datePicker);
				
			}else if(className.matches("Task")) {
				DatabaseConnection.updateItemChecklistItem(Task.class, itemToBeUpdatedIndex, textAreaUpdate.getText(),
						updateSelectTypeChoiceBox.getValue(), updateSelectFrequencyChoiceBox.getValue(),
						updateStatus.getValue(), datePicker);
			}
			
			Stage stage = (Stage) textAreaUpdate.getScene().getWindow();
			stage.close();
		}
	}

}
