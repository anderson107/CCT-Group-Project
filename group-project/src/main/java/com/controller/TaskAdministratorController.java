package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.saturn.Main;
import com.saturn.dao.DatabaseConnection;
import com.saturn.model.checklists.ChecklistCategory;
import com.saturn.model.checklists.ChecklistSuperClass;
import com.saturn.model.checklists.FireWarden;
import com.saturn.model.checklists.HealthSafetyChecklist;
import com.saturn.model.checklists.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.Getter;

public class TaskAdministratorController implements Initializable {

	// private fields //
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
	private TableColumn<ChecklistSuperClass, LocalDate> creationDate;

	@FXML
	private TableColumn<ChecklistSuperClass, LocalDate> dueDate;

	@FXML
	private ChoiceBox<String> taskAdministratorChoicebox;

	@FXML
	private CheckBox checkbox1;
	
	protected static ObservableList<ChecklistSuperClass> selected;

	private List<ChecklistSuperClass> checklistList=new ArrayList<>();

	// constructor //
	public TaskAdministratorController() {
		selected = FXCollections.observableArrayList();
	}

	// private and public methods //

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
		creationDate.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, LocalDate>("creationDate"));
		checklistList.addAll(DatabaseConnection.loadAllData(FireWarden.class));
		checklistList.addAll(DatabaseConnection.loadAllData(HealthSafetyChecklist.class));
		checklistList.addAll(DatabaseConnection.loadAllData(Task.class));

		// it sets check boxes to each object
		for (ChecklistSuperClass c : checklistList) {
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
			for (ChecklistSuperClass c : checklistList) {
				c.setCheckbox(new CheckBox());
			}
			tableView.getItems().setAll(checklistList);
		}

		else if (taskAdministratorChoicebox.getValue().matches(ChecklistCategory.FIRE_WARDEN.getCategory())) {
			checklistList.addAll(DatabaseConnection.loadAllData(FireWarden.class));
			// it sets check boxes to each object
			for (ChecklistSuperClass c : checklistList) {
				c.setCheckbox(new CheckBox());
			}
			tableView.getItems().setAll(checklistList);
		}

		else if (taskAdministratorChoicebox.getValue().matches(ChecklistCategory.TASK.getCategory())) {
			checklistList.addAll(DatabaseConnection.loadAllData(Task.class));
			// it sets check boxes to each object
			for (ChecklistSuperClass c : checklistList) {
				c.setCheckbox(new CheckBox());
			}
			tableView.getItems().setAll(checklistList);

		} else if (taskAdministratorChoicebox.getValue().matches(ChecklistCategory.ALL.getCategory())) {
			checklistList.addAll(DatabaseConnection.loadAllData(FireWarden.class));
			checklistList.addAll(DatabaseConnection.loadAllData(HealthSafetyChecklist.class));
			checklistList.addAll(DatabaseConnection.loadAllData(Task.class));
			// it sets check boxes to each object
			for (ChecklistSuperClass c : checklistList) {
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
			}
		}

		if (!delete.isEmpty()) {
			// Confirmation window of delete item and return if "no" is clicked
			int index = JOptionPane.showConfirmDialog(null, "Delete the selected item(s)?", "Confirmation Window",
					JOptionPane.YES_NO_OPTION);
			if (index == 1) {
				return;
			} else {
				for (ChecklistSuperClass d : delete) {
					DatabaseConnection.delete((ChecklistSuperClass) d);
				}
			}
		}

		checklistList.removeAll(delete);
		tableView.getItems().setAll(checklistList);

	}
	
	// it refreshes the table view 
	private void refresh() {
		checklistList.clear();
		checklistList.addAll(DatabaseConnection.loadAllData(FireWarden.class));
		checklistList.addAll(DatabaseConnection.loadAllData(HealthSafetyChecklist.class));
		checklistList.addAll(DatabaseConnection.loadAllData(Task.class));
		
		for(ChecklistSuperClass e: checklistList) {
			e.setCheckbox(new CheckBox());
		}
		
		tableView.getItems().setAll(checklistList);
	}
	
	@FXML
	private void openUpdateTaskWindow() {

		Stage stage= null;
		int index = 0;
		for (ChecklistSuperClass cl : checklistList) {
			if (cl.getCheckbox().isSelected()) {
				selected.add(cl);
				index++;
			}
		}
		if (index == 0 || index > 1) {
			JOptionPane.showMessageDialog(null, "Select 1 item");
			return;
		} else {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.UPDATE_ITEM_CHECKLIST));
				Parent root1 = (Parent) fxmlLoader.load();
				stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner(Main.stage);
				stage.setTitle("Update Task");
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			stage.setOnHidden((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					refresh();
				}
			});
		}
	}
}
