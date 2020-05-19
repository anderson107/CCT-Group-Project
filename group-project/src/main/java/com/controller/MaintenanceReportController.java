package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import com.saturn.model.maintenance.Maintenance;
import com.saturn.model.reports.MaintenanceReport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class MaintenanceReportController implements Initializable {

	@FXML
	private ChoiceBox<String> choicebox;

	@FXML
	private Button backButton;

	// it populates the choice box with a list of options
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("All", "Expired", "Done");
		// populate the Choice box;
		choicebox.setItems(list);
		choicebox.setValue("All");
	}

	// it generates maintenance report
	@FXML
	private void printReport() {

		Dao dao = new Dao();
		List<Maintenance> maintenanceList = new ArrayList<>();
		maintenanceList.addAll(dao.loadAllData(Maintenance.class));
		ListIterator<Maintenance> iterator = maintenanceList.listIterator();

		String choiceString = choicebox.getValue();

			if (choiceString.matches("Expired")) {

				// it removes all task that is not pending
				while (iterator.hasNext()) {
					Maintenance t = iterator.next();
					if (!t.getNextDate().isBefore(LocalDate.now())) {
						iterator.remove();
					}
				}
				closeTaskReportWindow();
				new MaintenanceReport(maintenanceList, "Maintenance Report", "Maintenance.pdf", "Maintenance.jasper");

			} else if (choiceString.matches("Done")) {

				// it removes all task that is not Done
				while (iterator.hasNext()) {
					Maintenance t = iterator.next();
					if (!t.getNextDate().isAfter(LocalDate.now())) {
						iterator.remove();
					}
				}
				closeTaskReportWindow();
				new MaintenanceReport(maintenanceList, "Maintenance Report", "Maintenance.pdf", "Maintenance.jasper");

			} else if (choiceString.matches("All")) {
				closeTaskReportWindow();
				new MaintenanceReport(maintenanceList, "Maintenance Report", "Maintenance.pdf", "Maintenance.jasper");
			}
		}

	// it closes the window
	@FXML
	private void closeTaskReportWindow() {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

}
