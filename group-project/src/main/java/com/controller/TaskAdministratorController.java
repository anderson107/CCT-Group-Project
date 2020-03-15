package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.Checklist;
import com.saturn.model.ChecklistCategory;
import com.saturn.model.ChecklistSuperClass;
import com.saturn.model.FireWarden;
import com.saturn.model.HealthSafetyChecklist;
import com.saturn.model.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TaskAdministratorController implements Initializable {

	@FXML
	private TableView<ChecklistSuperClass> tableView;

	@FXML
	private TableColumn<ChecklistSuperClass, String> checkbox;

	@FXML
	private TableColumn<ChecklistSuperClass, Integer> id;

	@FXML
	private TableColumn<ChecklistSuperClass, String> description;

	@FXML
	private TableColumn<ChecklistSuperClass, String> frequency;

	@FXML
	private TableColumn<ChecklistSuperClass, String> status;

	@FXML
	private TableColumn<ChecklistSuperClass, LocalDate> dueDate;

	@FXML
	private ChoiceBox<String> taskAdministratorChoicebox;

	@FXML
	private CheckBox checkbox1;

	private List<ChecklistSuperClass> checklistList;

	public TaskAdministratorController() {
		checklistList = new ArrayList<>();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll(ChecklistCategory.HEALTH_SAFETY.getCategory(), ChecklistCategory.FIRE_WARDEN.getCategory(),
				ChecklistCategory.TASK.getCategory(), ChecklistCategory.ALL.getCategory());
		// populate the Choice box;
		taskAdministratorChoicebox.setItems(list);
		taskAdministratorChoicebox.setValue(ChecklistCategory.ALL.getCategory());

		checkbox.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("checkbox"));
		id.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, Integer>("id"));
		description.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("itemDescription"));
		frequency.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("frequency"));
		status.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("status"));
		dueDate.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, LocalDate>("dueDate"));
		checklistList.addAll(DatabaseConnection.loadAllData(FireWarden.class));
		checklistList.addAll(DatabaseConnection.loadAllData(HealthSafetyChecklist.class));
		checklistList.addAll(DatabaseConnection.loadAllData(Task.class));
		
		// it sets check boxes to each object
		for(ChecklistSuperClass c: checklistList) {
			c.setCheckbox(new CheckBox());
		}

		tableView.getItems().setAll(checklistList);

	}

	@FXML
	private void updateTable() {
		checklistList.clear();
		if (taskAdministratorChoicebox.getValue().matches(ChecklistCategory.HEALTH_SAFETY.getCategory())) {
			checklistList.addAll(DatabaseConnection.loadAllData(HealthSafetyChecklist.class));
			// it sets check boxes to each object
			for(ChecklistSuperClass c: checklistList) {
				c.setCheckbox(new CheckBox());
			}
			tableView.getItems().setAll(checklistList);
		}

		else if (taskAdministratorChoicebox.getValue().matches(ChecklistCategory.FIRE_WARDEN.getCategory())) {
			checklistList.addAll(DatabaseConnection.loadAllData(FireWarden.class));
			// it sets check boxes to each object
			for(ChecklistSuperClass c: checklistList) {
				c.setCheckbox(new CheckBox());
			}
			tableView.getItems().setAll(checklistList);
		}

		else if (taskAdministratorChoicebox.getValue().matches(ChecklistCategory.TASK.getCategory())) {
			checklistList.addAll(DatabaseConnection.loadAllData(Task.class));
			// it sets check boxes to each object
			for(ChecklistSuperClass c: checklistList) {
				c.setCheckbox(new CheckBox());
			}
			tableView.getItems().setAll(checklistList);
			
		} else if (taskAdministratorChoicebox.getValue().matches(ChecklistCategory.ALL.getCategory())) {
			checklistList.addAll(DatabaseConnection.loadAllData(FireWarden.class));
			checklistList.addAll(DatabaseConnection.loadAllData(HealthSafetyChecklist.class));
			checklistList.addAll(DatabaseConnection.loadAllData(Task.class));
			// it sets check boxes to each object
			for(ChecklistSuperClass c: checklistList) {
				c.setCheckbox(new CheckBox());
			}
			tableView.getItems().setAll(checklistList);
		}
	}

	@FXML
	private void deleteRow() {
		
		
		ObservableList<ChecklistSuperClass> delete = FXCollections.observableArrayList();

		for (ChecklistSuperClass c : checklistList) {
			if (c.getCheckbox().isSelected()) {
				delete.add(c);
				
			// Confirmation window of delete item and return if "no" is clicked
			int index=JOptionPane.showInternalConfirmDialog(null, "Delete the selected item(s)?");
			if(index==1 || index==2) {
				return;
			}else {
				DatabaseConnection.deleteItemFromChecklist((Checklist) c);
			}
				
			}
		}

		checklistList.removeAll(delete);
		tableView.getItems().setAll(checklistList);

	}

}
