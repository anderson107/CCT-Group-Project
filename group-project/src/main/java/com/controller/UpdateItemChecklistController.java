package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.saturn.model.ChecklistSuperClass;

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
	private ChoiceBox<String>updateStatus;
	
	@FXML
	private TextArea textAreaUpdate;
	
	
	@FXML
	private void closeUpdateTaskWindow() {
		//Stage stage = (Stage) updateItemBackButton.getScene().getWindow();
		//stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ChecklistSuperClass updateItem = TaskAdministratorController.selected.get(0);
		ObservableList<String> list = FXCollections.observableArrayList();
		   list.addAll("Health and Safety", "Fire Warden","Task");
		  //populate the Choice box;  
		   updateSelectTypeChoiceBox.setItems(list);
		  
		  ObservableList<String> list1 = FXCollections.observableArrayList();
		   list1.addAll("Daily", "Weekly","Biweekly","Monthly");
		  //populate the Choice box;  
		   updateSelectFrequencyChoiceBox.setItems(list1);
		   updateSelectFrequencyChoiceBox.setValue(TaskAdministratorController.selected.get(0).getFrequency());
		   
		   ObservableList<String> list2 = FXCollections.observableArrayList();
		   list2.addAll("Pending", "Done");
		  //populate the Choice box;  
		   updateStatus.setItems(list2);
		   updateStatus.setValue(updateItem.getStatus());
		   
		   textAreaUpdate.setText(updateItem.getItemDescription());
		
		   TaskAdministratorController.selected.remove(updateItem);
	}
	
}
