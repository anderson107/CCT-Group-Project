package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.saturn.model.Checklist;
import com.saturn.model.ChecklistSuperClass;
import com.saturn.model.FireWarden;
import com.saturn.model.HealthSafetyChecklist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TaskAdministratorController implements Initializable {
	
	@FXML
	private TableView<ChecklistSuperClass>tableView;

	@FXML
	private TableColumn<ChecklistSuperClass, String>checkbox;
	
	@FXML
	private TableColumn<ChecklistSuperClass, Integer>id;
	
	@FXML
	private TableColumn<ChecklistSuperClass, String>description;
	
	@FXML
	private TableColumn<ChecklistSuperClass, String>frequency;
	
	@FXML
	private TableColumn<ChecklistSuperClass, String>status;

	private List<ChecklistSuperClass> fire;
	
	public TaskAdministratorController() {
		fire = new ArrayList<>();
		fire.add(new FireWarden("Verifiquei todas a entradas, sem problema nenhum, vamos ficar em  casa", "done", "Daily"));
		fire.add(new FireWarden("Verificou todas a entradas, sem problema nenhum, vamos ficar em  casa", "done", "Daily"));
		fire.add(new HealthSafetyChecklist("Verificarei todas a entradas, sem problema nenhum, vamos ficar em  casa", "done", "Daily"));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		checkbox.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("checkbox"));
		id.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, Integer>("id"));
		description.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("itemDescription"));
		frequency.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("frequency"));
		status.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("status"));

	    tableView.getItems().setAll(fire);
		
	}
		
	@FXML
	private void deleteRow() {
		ObservableList<ChecklistSuperClass>delete = FXCollections.observableArrayList();
		for(ChecklistSuperClass c: fire) {
			if(c.getCheckbox().isSelected()) {
				delete.add(c);
			}
		}
		fire.removeAll(delete);
		tableView.getItems().setAll(fire);
		//tableView.refresh();
	}
	
	 
}
