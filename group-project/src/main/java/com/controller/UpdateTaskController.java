package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.saturn.model.Validation;
import com.saturn.model.task.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UpdateTaskController implements Initializable{

	@FXML
	private TextArea textArea;
	
	@FXML
	private TextArea actionArea;
	
	@FXML
	private ChoiceBox<String> status;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label descriptionLabel;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private Label dateLabel;
	
	@FXML
	private Label actionLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// retrieving the selected item
		Task task = TaskTableController.selected.get(0);
		
		ObservableList<String> list = FXCollections.observableArrayList();
		
		list.addAll("Pending","Done");
		
		// populate the Choice box;
		status.setItems(list);
		status.setValue(task.getStatus());
		textArea.setText(task.getItemDescription());
		datePicker.setValue(task.getDate());
		actionArea.setText(task.getAction());
	}
	
	@FXML
	private void updateTask() {
		Dao dao = new Dao();
		int id = TaskTableController.selected.get(0).getId();
		
		// validation
		boolean textAreaI = Validation.isTextAreaEmpty(textArea, descriptionLabel, "Required");
		boolean textAreaII = Validation.isTextAreaEmpty(actionArea, actionLabel, "Required");
		boolean choicebox = Validation.isChoiceBoxSelected(status, statusLabel, "Required");
		boolean datepicker = Validation.isDateEmpty(datePicker, dateLabel, "Required");
		
		// update item in the database
		if(textAreaI && textAreaII && choicebox && datepicker) {
			
			dao.updateTask(id, textArea.getText(), actionArea.getText(), datePicker.getValue(), status.getValue());
			
			TaskTableController.selected.clear();
			
			closeWindow();
		}
	}
	
	@FXML
	private void closeWindow() {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}
}
