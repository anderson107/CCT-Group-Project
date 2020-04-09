package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import com.saturn.model.Validation;
import com.saturn.model.reports.TaskReport;
import com.saturn.model.task.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class TaskReportController implements Initializable {

	@FXML
	private ChoiceBox<String> choicebox;

	@FXML
	private Button taskReportBackButton;

	@FXML
	private Label startDateLabel;

	@FXML
	private Label endtDateLabel;

	@FXML
	private DatePicker date1;

	@FXML
	private DatePicker date2;

	@FXML
	private Label choiceLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("Pending", "Done", "All");
		// populate the Choice box;
		choicebox.setItems(list);
		choicebox.setValue("Pending");
	}

	@FXML
	private void printReport() {

		Dao dao = new Dao();
		//boolean startDate = Validation.isDateEmpty(date1, startDateLabel, "Required");
		//boolean endDate = Validation.isDateEmpty(date2, endtDateLabel, "Required");
		boolean choice = Validation.isChoiceBoxSelected(choicebox, choiceLabel, "Required");
		
		List<Task> taskList = new ArrayList<>();
		taskList.addAll(dao.loadAllTask());	
				
		ListIterator<Task> iterator = taskList.listIterator();
		
		if (choice) {

			String choiceString = choicebox.getValue();

			if (choiceString.matches("Pending")) {
				
				while(iterator.hasNext()) {
					Task t = iterator.next();
					if(!t.getStatus().matches("Pending")) {
						iterator.remove();
					}
				}
				new TaskReport(taskList);
				return;
				
			} else if (choiceString.matches("Done")) {
				
				while(iterator.hasNext()) {
					Task t = iterator.next();
					if(!t.getStatus().matches("Done")) {
						iterator.remove();
					}
				}
				new TaskReport(taskList);
				return;

			} else if (choiceString.matches("All")) {
				new TaskReport(taskList);
				return;
			}
		}
	}

	@FXML
	private void closeTaskReportWindow() {
		Stage stage = (Stage) taskReportBackButton.getScene().getWindow();
		stage.close();
	}

}
