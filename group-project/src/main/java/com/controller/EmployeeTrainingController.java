package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.hibernate.Hibernate;

import com.saturn.dao.DataSource;
import com.saturn.dao.DatabaseConnection;
import com.saturn.model.employee.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class EmployeeTrainingController implements Initializable {

	@FXML
	private ChoiceBox<String> choicebox;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<String> list = FXCollections.observableArrayList();
		
		List<Employee>empList = new ArrayList<>();
		//Hibernate.initialize(empList.get(0));
		empList.addAll(DatabaseConnection.loadAllData(Employee.class));
		
		for(Employee e: empList) {
			list.add(e.getFirstName()+" "+e.getLastName());
		}
		choicebox.setItems(list);
		
	}
	
}
