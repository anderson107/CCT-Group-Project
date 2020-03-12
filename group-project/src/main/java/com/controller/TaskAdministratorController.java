package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.saturn.model.Checklist;
import com.saturn.model.FireWarden;
import com.saturn.model.HealthSafetyChecklist;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TaskAdministratorController implements Initializable {
	
	@FXML
	private TableView<Checklist>tableView;

	@FXML
	private TableColumn<Checklist, String>checkbox;
	
	@FXML
	private TableColumn<Checklist, Integer>id;
	
	@FXML
	private TableColumn<Checklist, String>description;
	
	@FXML
	private TableColumn<Checklist, String>frequency;
	
	@FXML
	private TableColumn<Checklist, String>status;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		checkbox.setCellValueFactory(new PropertyValueFactory<Checklist, String>("checkbox"));
		id.setCellValueFactory(new PropertyValueFactory<Checklist, Integer>("id"));
		description.setCellValueFactory(new PropertyValueFactory<Checklist, String>("itemDescription"));
		frequency.setCellValueFactory(new PropertyValueFactory<Checklist, String>("frequency"));
		status.setCellValueFactory(new PropertyValueFactory<Checklist, String>("status"));

	    tableView.getItems().setAll(parseUserList());
		
	}
	
	private List<Checklist>parseUserList(){
		List<Checklist>fire = new ArrayList<>();
		fire.add(new FireWarden("Verificar todas a entradas, sem problema nenhum, vamos ficar em  casa", "done", "Daily"));
		fire.add(new FireWarden("Verificar todas a entradas, sem problema nenhum, vamos ficar em  casa", "done", "Daily"));
		fire.add(new HealthSafetyChecklist("Verificar todas a entradas, sem problema nenhum, vamos ficar em  casa", "done", "Daily"));
		return fire;
		
	}
	
	 
}
