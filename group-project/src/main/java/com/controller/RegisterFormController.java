package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.saturn.model.Validation;
import com.saturn.model.employee.Employee;
import com.saturn.model.training.EmployeeHSE;
import com.saturn.model.training.EmployeeSeaChange;
import com.saturn.model.training.EmployeeVirtualAcademy;
import com.saturn.model.training.HSETraining;
import com.saturn.model.training.SeaChangeTraining;
import com.saturn.model.training.TrainingSuperClass;
import com.saturn.model.training.VirtualAcademyTraining;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegisterFormController {

	@FXML
	private TextField firstNameTF;

	@FXML
	private TextField lastNameTF;

	@FXML
	private TextField emailTF;

	@FXML
	private DatePicker dob;

	@FXML
	private TextField mobileTF;

	@FXML
	private TextField telephoneTF;

	@FXML
	private TextField addressTF;

	@FXML
	private TextField cityTF;

	@FXML
	private Button backToMenuButton;

	@FXML
	private Label firstNameLB;

	@FXML
	private Label lastNameLB;

	@FXML
	private Label emailLB;

	@FXML
	private Label dobLB;

	@FXML
	private Label mobileLB;

	@FXML
	private Label addressLB;

	@FXML
	private Label cityLB;

	@FXML
	private void backToMenu() {
		Stage stage = (Stage) backToMenuButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void saveEmployeeDatabase() {

		Dao dao = new Dao();
		// validation for the entry fields
		boolean firstName = Validation.isTextFieldValid(firstNameTF, firstNameLB, "Required");
		boolean lastName = Validation.isTextFieldValid(lastNameTF, lastNameLB, "Required");
		boolean email = Validation.isEmailValid(emailTF, emailLB, "Required");
		boolean dob1 = Validation.isDateEmpty(dob, dobLB, "Required");
		boolean mobile = Validation.isPhoneValid(mobileTF, mobileLB, "Required");
		boolean address = Validation.isTextFieldEmpty(addressTF, addressLB, "Required");
		boolean city = Validation.isTextFieldValid(cityTF, cityLB, "Required");

		// the code below creates a new employee and adds it to all the training 
		
		// if the user passes the validation an employee object is created
		if (firstName && lastName && email && dob1 && mobile && address && city) {

			Employee employee = new Employee(firstNameTF.getText(), lastNameTF.getText(), emailTF.getText(), dob.getValue(),
					mobileTF.getText(), telephoneTF.getText(), addressTF.getText(), cityTF.getText());

			// adding the employee to the database
			dao.add(employee);
			
			// retrieving all the employee from the database
			List<Employee>emps = new ArrayList<>();
			emps.addAll(dao.loadAllData(Employee.class));
			
			// getting the last entry that is the employee that was added above
			Employee emp = emps.get(emps.size()-1);

			// retrieving all the training in the database
			List<TrainingSuperClass> training = new ArrayList<>();
			training.addAll(dao.loadTrainingItems());

			if(training.size()==0) {
				backToMenu();
			}
			
			// adding employee to all the training
			for (TrainingSuperClass e : training) {

				if (e instanceof HSETraining) {
					EmployeeHSE hse = new EmployeeHSE();
					hse.setTraining(e);
					hse.setEmployee(emp);
					hse.setStatus("Pending");
					hse.setDate(null);
					dao.add(hse);
				} else if (e instanceof SeaChangeTraining) {
					EmployeeSeaChange seaChange = new EmployeeSeaChange();
					seaChange.setTraining(e);
					seaChange.setEmployee(emp);
					seaChange.setStatus("Pending");
					seaChange.setDate(null);
					dao.add(seaChange);
				} else if (e instanceof VirtualAcademyTraining) {
					EmployeeVirtualAcademy virtual = new EmployeeVirtualAcademy();
					virtual.setTraining(e);
					virtual.setEmployee(emp);
					virtual.setStatus("Pending");
					virtual.setDate(null);
					dao.add(virtual);
				}
			}

			// closes the window
			backToMenu();
		}

	}
}
