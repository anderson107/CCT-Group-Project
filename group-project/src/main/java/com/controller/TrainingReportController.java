package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.saturn.model.employee.Employee;
import com.saturn.model.reports.TrainingReport;
import com.saturn.model.training.EmployeeHSE;
import com.saturn.model.training.EmployeeSeaChange;
import com.saturn.model.training.EmployeeTraining;
import com.saturn.model.training.EmployeeVirtualAcademy;
import com.saturn.model.training.TrainingReportObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TrainingReportController implements Initializable {

	@FXML
	private Button backButton;

	@FXML
	private ChoiceBox<String> employeeChoicebox;

	@FXML
	private ChoiceBox<String> statusChoicebox;

	@FXML
	private ChoiceBox<String> trainingChoicebox;

	private List<Employee> empList;

	// it initialises the choice boxes with a list of options
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Dao dao = new Dao();
		empList = new ArrayList<>();

		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("All Status", "Pending", "Done");
		statusChoicebox.setItems(list);
		statusChoicebox.setValue("All Status");

		ObservableList<String> list2 = FXCollections.observableArrayList();
		list2.add("All Employees");
		empList.addAll(dao.loadAllData(Employee.class));
		for (Employee e : empList) {
			list2.add(e.getFirstName() + " " + e.getLastName());
		}
		employeeChoicebox.setItems(list2);
		employeeChoicebox.setValue("All Employees");

		ObservableList<String> list3 = FXCollections.observableArrayList();
		list3.addAll("All Training", "SeaChange", "HSE", "Virtual Academy");
		trainingChoicebox.setItems(list3);
		trainingChoicebox.setValue("All Training");
	}

	// it generates a customised report
	@FXML
	private void printReport() {

		Dao dao = new Dao();
		
		String employeeName = employeeChoicebox.getValue();
		String status = statusChoicebox.getValue();
		String trainingType = trainingChoicebox.getValue();
		String AllEmployee = "All Employees";
		String AllStatus = "All Status";
		String AllTraining = "All Training";
		int id = 0;
		
		List<EmployeeTraining> list = new ArrayList<>();
		list.addAll(dao.loadAllData(EmployeeHSE.class));
		list.addAll(dao.loadAllData(EmployeeSeaChange.class));
		list.addAll(dao.loadAllData(EmployeeVirtualAcademy.class));

		List<TrainingReportObject> reportList = new ArrayList<>();

		// it sets the class name that comes null since it is not in the database
		for (EmployeeTraining et : list) {
			if (et instanceof EmployeeHSE) {
				et.getTraining().setClassName("HSE");
			} else if (et instanceof EmployeeSeaChange) {
				et.getTraining().setClassName("SeaChange");
			} else if (et instanceof EmployeeVirtualAcademy) {
				et.getTraining().setClassName("Virtual Academy");
			}
		}

		if (employeeName.matches(AllEmployee) && status.matches(AllStatus) && trainingType.matches(AllTraining)) {

			for (Employee e : empList) {

				for (EmployeeTraining et : list) {

					if (et.getEmployee().equals(e)) {

						TrainingReportObject trainingObj = new TrainingReportObject();
						trainingObj.setFirstName(et.getEmployee().getFirstName());
						trainingObj.setLastName(et.getEmployee().getLastName());
						trainingObj.setTraining(et.getTraining().getTraining());
						trainingObj.setClassName(et.getTraining().getClassName());
						trainingObj.setStatus(et.getStatus());
						trainingObj.setDate(et.getDate());

						reportList.add(trainingObj);
					}
				}
			}
		} else if (employeeName.matches(AllEmployee) && !status.matches(AllStatus) && !trainingType.matches(AllTraining)) {

			for (Employee e : empList) {

				for (EmployeeTraining et : list) {

					if (et.getEmployee().equals(e) && et.getStatus().matches(status)
							&& et.getTraining().getClassName().matches(trainingType)) {

						TrainingReportObject trainingObj = new TrainingReportObject();
						trainingObj.setFirstName(et.getEmployee().getFirstName());
						trainingObj.setLastName(et.getEmployee().getLastName());
						trainingObj.setTraining(et.getTraining().getTraining());
						trainingObj.setClassName(et.getTraining().getClassName());
						trainingObj.setStatus(et.getStatus());
						trainingObj.setDate(et.getDate());

						reportList.add(trainingObj);
					}
				}
			}
		} else if (employeeName.matches(AllEmployee) && status.matches(AllStatus) && !trainingType.matches(AllTraining)) {

			for (Employee e : empList) {

				for (EmployeeTraining et : list) {

					if (et.getEmployee().equals(e) && et.getTraining().getClassName().matches(trainingType)) {

						TrainingReportObject trainingObj = new TrainingReportObject();
						trainingObj.setFirstName(et.getEmployee().getFirstName());
						trainingObj.setLastName(et.getEmployee().getLastName());
						trainingObj.setTraining(et.getTraining().getTraining());
						trainingObj.setClassName(et.getTraining().getClassName());
						trainingObj.setStatus(et.getStatus());
						trainingObj.setDate(et.getDate());

						reportList.add(trainingObj);
					}
				}
			}

		} else if (!employeeName.matches(AllEmployee) && status.matches(AllStatus) && trainingType.matches(AllTraining)) {

			List<Employee> emp = dao
					.queryDB("from Employee WHERE CONCAT(first_name,' ',last_name)='" + employeeName + "'");

			if (emp.size() == 0 || emp.size() > 1) {
				return;
			} else {
				id = emp.get(0).getId();
			}
			
			for (EmployeeTraining et : list) {

				if (et.getEmployee().getId() == id) {
					
					TrainingReportObject trainingObj = new TrainingReportObject();
					trainingObj.setFirstName(et.getEmployee().getFirstName());
					trainingObj.setLastName(et.getEmployee().getLastName());
					trainingObj.setTraining(et.getTraining().getTraining());
					trainingObj.setClassName(et.getTraining().getClassName());
					trainingObj.setStatus(et.getStatus());
					trainingObj.setDate(et.getDate());

					reportList.add(trainingObj);
				}
			}
			
		} else if (!employeeName.matches(AllEmployee) && !status.matches(AllStatus) && trainingType.matches(AllTraining)) {

			List<Employee> emp = dao
					.queryDB("from Employee WHERE CONCAT(first_name,' ',last_name)='" + employeeName + "'");

			if (emp.size() == 0 || emp.size() > 1) {
				return;
			} else {
				id = emp.get(0).getId();
			}

			for (EmployeeTraining et : list) {

				if (et.getEmployee().getId() == id && et.getStatus().matches(status)) {

					TrainingReportObject trainingObj = new TrainingReportObject();
					trainingObj.setFirstName(et.getEmployee().getFirstName());
					trainingObj.setLastName(et.getEmployee().getLastName());
					trainingObj.setTraining(et.getTraining().getTraining());
					trainingObj.setClassName(et.getTraining().getClassName());
					trainingObj.setStatus(et.getStatus());
					trainingObj.setDate(et.getDate());

					reportList.add(trainingObj);
				}
			}
		} else if (!employeeName.matches(AllEmployee) && !status.matches(AllStatus) && !trainingType.matches(AllTraining)) {

			List<Employee> emp = dao
					.queryDB("from Employee WHERE CONCAT(first_name,' ',last_name)='" + employeeName + "'");

			if (emp.size() == 0 || emp.size() > 1) {
				return;
			} else {
				id = emp.get(0).getId();
			}

			for (EmployeeTraining et : list) {

				if (et.getEmployee().getId() == id && et.getStatus().matches(status)
						&& et.getTraining().getClassName().matches(trainingType)) {

					TrainingReportObject trainingObj = new TrainingReportObject();
					trainingObj.setFirstName(et.getEmployee().getFirstName());
					trainingObj.setLastName(et.getEmployee().getLastName());
					trainingObj.setTraining(et.getTraining().getTraining());
					trainingObj.setClassName(et.getTraining().getClassName());
					trainingObj.setStatus(et.getStatus());
					trainingObj.setDate(et.getDate());

					reportList.add(trainingObj);
				}
			}
		} else if (!employeeName.matches(AllEmployee) && status.matches(AllStatus) && !trainingType.matches(AllTraining)) {

			List<Employee> emp = dao
					.queryDB("from Employee WHERE CONCAT(first_name,' ',last_name)='" + employeeName + "'");

			if (emp.size() == 0 || emp.size() > 1) {
				return;
			} else {
				id = emp.get(0).getId();
			}

			for (EmployeeTraining et : list) {

				if (et.getEmployee().getId() == id && et.getTraining().getClassName().matches(trainingType)) {

					TrainingReportObject trainingObj = new TrainingReportObject();
					trainingObj.setFirstName(et.getEmployee().getFirstName());
					trainingObj.setLastName(et.getEmployee().getLastName());
					trainingObj.setTraining(et.getTraining().getTraining());
					trainingObj.setClassName(et.getTraining().getClassName());
					trainingObj.setStatus(et.getStatus());
					trainingObj.setDate(et.getDate());

					reportList.add(trainingObj);
				}
			}
		} else if (employeeName.matches(AllEmployee) && !status.matches(AllStatus) && trainingType.matches(AllTraining)) {

			for (Employee e : empList) {

				for (EmployeeTraining et : list) {

					if (et.getEmployee().equals(e) && et.getStatus().matches(status)) {

						TrainingReportObject trainingObj = new TrainingReportObject();
						trainingObj.setFirstName(et.getEmployee().getFirstName());
						trainingObj.setLastName(et.getEmployee().getLastName());
						trainingObj.setTraining(et.getTraining().getTraining());
						trainingObj.setClassName(et.getTraining().getClassName());
						trainingObj.setStatus(et.getStatus());
						trainingObj.setDate(et.getDate());

						reportList.add(trainingObj);
					}
				}
			}
		} 
		new TrainingReport(reportList,"Training Report", "Training.pdf", "Training.jasper");
	}

	// it closes the window
	@FXML
	private void closeReportWindow() {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

}
