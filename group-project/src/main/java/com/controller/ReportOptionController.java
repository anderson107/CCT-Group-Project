package com.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.saturn.model.maintenance.Maintenance;
import com.saturn.model.reports.MaintenanceReport;
import com.saturn.model.reports.TaskReport;
import com.saturn.model.task.Task;

import javafx.fxml.FXML;

public class ReportOptionController {

	@FXML
	private void generateChecklist() {
		ChecklistReportController report = new ChecklistReportController();
		report.generateTodayLate();
	}
	
	@FXML
	private void generateTask() {
		Dao dao = new Dao();
		List<Task> taskList = new ArrayList<>();
		taskList.addAll(dao.loadAllTask());
		List<Task>print = new ArrayList<>();
		
		for(Task t: taskList) {
			if(t.getStatus().matches("Pending")) {
				print.add(t);
			}
		}
		
		new TaskReport(print);
	}
	
	@FXML
	private void generateMaintenance() {
		Dao dao = new Dao();
		List<Maintenance> maintenanceList = new ArrayList<>();
		maintenanceList.addAll(dao.loadAllMaintenance());
		ListIterator<Maintenance> iterator = maintenanceList.listIterator();


				// it removes all task that is not pending
				while (iterator.hasNext()) {
					Maintenance t = iterator.next();
					if (!t.getNextDate().isBefore(LocalDate.now())) {
						iterator.remove();
					}
				}
				new MaintenanceReport(maintenanceList);
	}
}
