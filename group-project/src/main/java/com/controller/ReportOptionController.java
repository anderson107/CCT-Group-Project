package com.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.saturn.model.employee.Employee;
import com.saturn.model.maintenance.Maintenance;
import com.saturn.model.reports.MaintenanceReport;
import com.saturn.model.reports.TaskReport;
import com.saturn.model.reports.TrainingReport;
import com.saturn.model.task.Task;
import com.saturn.model.training.EmployeeHSE;
import com.saturn.model.training.EmployeeSeaChange;
import com.saturn.model.training.EmployeeTraining;
import com.saturn.model.training.EmployeeVirtualAcademy;
import com.saturn.model.training.TrainingReportObject;

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
		taskList.addAll(dao.loadAllData(Task.class));
		
		ListIterator<Task> iterator = taskList.listIterator();
		
		while(iterator.hasNext()) {
			Task task = iterator.next();
			
			if(!task.getStatus().matches("Pending")) {
				iterator.remove();
			}
		}
		
		new TaskReport(taskList, "Task Report", "Task.pdf", "Task.jasper");
	}
	
	@FXML
	private void generateMaintenance() {
		Dao dao = new Dao();
		List<Maintenance> maintenanceList = new ArrayList<>();
		maintenanceList.addAll(dao.loadAllData(Maintenance.class));
		ListIterator<Maintenance> iterator = maintenanceList.listIterator();

				// it removes all task that is not pending
				while (iterator.hasNext()) {
					Maintenance t = iterator.next();
					if (!t.getNextDate().isBefore(LocalDate.now())) {
						iterator.remove();
					}
				}
				new MaintenanceReport(maintenanceList, "Maintenance Report", "Maintenance.pdf", "Maintenance.jasper");
	}
	
	@FXML
	private void trainingEmployeeAllReport() {
		Dao dao = new Dao();
		List<EmployeeTraining> list = new ArrayList<>();
		list.addAll(dao.loadAllData(EmployeeHSE.class));
		list.addAll(dao.loadAllData(EmployeeSeaChange.class));
		list.addAll(dao.loadAllData(EmployeeVirtualAcademy.class));
		
		List<TrainingReportObject> custom = new ArrayList<>();
		
		List<Employee> employee = new ArrayList<>();
		employee.addAll(dao.loadAllData(Employee.class));

		for (Employee e : employee) {

			for (EmployeeTraining et : list) {

				if (et.getEmployee().equals(e) && et.getStatus().matches("Pending")) {
					
					if(et instanceof EmployeeHSE) {
						et.getTraining().setClassName("HSE");
					}else if(et instanceof EmployeeSeaChange) {
						et.getTraining().setClassName("SeaChange");
					}else if(et instanceof EmployeeVirtualAcademy) {
						et.getTraining().setClassName("Virtual Academy");
					}

					TrainingReportObject trainingObj = new TrainingReportObject();
					trainingObj.setFirstName(et.getEmployee().getFirstName());
					trainingObj.setLastName(et.getEmployee().getLastName());
					trainingObj.setTraining(et.getTraining().getTraining());
					trainingObj.setClassName(et.getTraining().getClassName());
					trainingObj.setStatus(et.getStatus());
					trainingObj.setDate(et.getDate());

					custom.add(trainingObj);
				}
			}
		}
		new TrainingReport(custom,"Training Report", "Training.pdf", "Training.jasper");
	}
}
