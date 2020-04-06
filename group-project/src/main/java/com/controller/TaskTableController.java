package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.saturn.Main;
import com.saturn.dao.DatabaseConnection;
import com.saturn.model.task.Task;

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

public class TaskTableController implements Initializable{

	@FXML
	private TableView<Task>tableView;
	
	@FXML
	private TableColumn<Task, CheckBox>select;

	@FXML
	private TableColumn<Task, Integer>id;
	
	@FXML
	private TableColumn<Task, String>description;
	
	@FXML
	private TableColumn<Task, String>status;
	
	@FXML
	private TableColumn<Task, LocalDate>creationDate;
	
	@FXML
	private TableColumn<Task, LocalDate>dueDate;
	
	@FXML
	private TableColumn<Task, String>actionTaken;

	protected static ObservableList<Task> selected;
	
	List<Task>taskList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		select.setCellValueFactory(new PropertyValueFactory<Task, CheckBox>("checkbox"));
		id.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
		description.setCellValueFactory(new PropertyValueFactory<Task, String>("itemDescription"));
		status.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
		dueDate.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("date"));
		creationDate.setCellValueFactory(new PropertyValueFactory<Task, LocalDate>("creationDate"));
		actionTaken.setCellValueFactory(new PropertyValueFactory<Task, String>("action"));
		
		taskList = new ArrayList<>();
		
		taskList.addAll(DatabaseConnection.loadAllData(Task.class));
		
		for(Task t: taskList) {
			t.setCheckbox(new CheckBox());
		}
		
		tableView.getItems().setAll(taskList);
	}
	
	@FXML
	private void deleteTask() {
		
		ObservableList<Task> delete = FXCollections.observableArrayList();
		for (Task task : taskList) {
			if (task.getCheckbox().isSelected()) {
				delete.add(task);
			}
		}

		if (!delete.isEmpty()) {
			// Confirmation window of delete item and return if "no" is clicked
			int index = JOptionPane.showConfirmDialog(null, "Delete the selected item(s)?", "Confirmation Window",
					JOptionPane.YES_NO_OPTION);
			if (index == 1) {
				return;
			} else {
				for (Task task : delete) {
					DatabaseConnection.delete(task);
				}
			}
		}

		taskList.removeAll(delete);
		tableView.getItems().setAll(taskList);

	}
	
	private void refresh() {
		taskList.clear();
		taskList.addAll(DatabaseConnection.loadAllData(Task.class));
		for (Task e : taskList) {
			e.setCheckbox(new CheckBox());
		}
		
		tableView.getItems().setAll(taskList);
	}
	
	@FXML
	private void updateTask() {
		
		selected = FXCollections.observableArrayList();
		
		Stage stage = null;
		int index = 0;
		for (Task cl : taskList) {
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
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.UPDATE_TASK));
				Parent root1 = (Parent) fxmlLoader.load();
				stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner(Main.stage);
				stage.setTitle("UPDATE TASK");
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
