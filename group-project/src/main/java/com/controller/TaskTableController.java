package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.task.Task;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
}
