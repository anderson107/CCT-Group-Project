package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

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
		List<Task> taskList = new ArrayList<>();
		taskList.addAll(dao.loadAllData(Task.class));
		ListIterator<Task> iterator = taskList.listIterator();

		String choiceString = choicebox.getValue();

			if (choiceString.matches("Pending")) {

				// it removes all task that is not pending
				while (iterator.hasNext()) {
					Task t = iterator.next();
					if (!t.getStatus().matches("Pending")) {
						iterator.remove();
					}
				}
				closeTaskReportWindow();
				new TaskReport(taskList);

			} else if (choiceString.matches("Done")) {

				// it removes all task that is not Done
				while (iterator.hasNext()) {
					Task t = iterator.next();
					if (!t.getStatus().matches("Done")) {
						iterator.remove();
					}
				}
				closeTaskReportWindow();
				new TaskReport(taskList);

			} else if (choiceString.matches("All")) {
				closeTaskReportWindow();
				new TaskReport(taskList);
			}
		}

	@FXML
	private void closeTaskReportWindow() {
		Stage stage = (Stage) taskReportBackButton.getScene().getWindow();
		stage.close();
	}

}
