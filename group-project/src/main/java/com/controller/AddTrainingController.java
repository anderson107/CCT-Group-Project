package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.saturn.model.Validation;
import com.saturn.model.employee.Employee;
import com.saturn.model.training.EmployeeHSE;
import com.saturn.model.training.EmployeeSeaChange;
import com.saturn.model.training.EmployeeVirtualAcademy;
import com.saturn.model.training.HSETraining;
import com.saturn.model.training.SeaChangeTraining;
import com.saturn.model.training.TrainingFactory;
import com.saturn.model.training.TrainingSuperClass;
import com.saturn.model.training.VirtualAcademyTraining;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class AddTrainingController implements Initializable {

	@FXML
	private ChoiceBox<String> trainingTypeChoiceBox;

	@FXML
	private Button addTrainingBackButton;

	@FXML
	private TextArea textArea;

	@FXML
	private Label textLabel;

	@FXML
	private Label choiceboxLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("SeaChange", "Virtual Academy", "HSE");
		// populate the Choice box;
		trainingTypeChoiceBox.setItems(list);

	}

	@FXML
	private void addTraining() {
		Dao dao = new Dao();
		// training validations
		boolean text = Validation.isTextAreaEmpty(textArea, textLabel, "Required");
		boolean choicebox = Validation.isChoiceBoxSelected(trainingTypeChoiceBox, choiceboxLabel, "Select training");

		// if the user passes the validation a new training is created
		if (text && choicebox) {
			// creating new object of type training and saving to the database
			TrainingSuperClass training = TrainingFactory.create(textArea.getText(), trainingTypeChoiceBox.getValue());
			dao.add(training);

			TrainingSuperClass lastEntry = null;

			// retrieving all employees from the database
			List<Employee> employees = new ArrayList<>();

			employees.addAll(dao.loadAllData(Employee.class));

			List<TrainingSuperClass> trainingList = new ArrayList<>();

			// if there is employee in the database it is added to the training created
			// above
			if (employees.size() != 0) {

				if (training instanceof SeaChangeTraining) {
					trainingList.addAll(dao.loadAllData(SeaChangeTraining.class));
					lastEntry = trainingList.get(trainingList.size() - 1);
					for (Employee e : employees) {
						EmployeeSeaChange seachange = new EmployeeSeaChange();
						seachange.setTraining(lastEntry);
						seachange.setEmployee(e);
						dao.add(seachange);
						closeAddTrainingWindow();
					}
				} else if (training instanceof HSETraining) {
					trainingList.addAll(dao.loadAllData(HSETraining.class));
					lastEntry = trainingList.get(trainingList.size() - 1);
					for (Employee e : employees) {
						EmployeeHSE hse = new EmployeeHSE();
						hse.setTraining(lastEntry);
						hse.setEmployee(e);
						dao.add(hse);
						closeAddTrainingWindow();
					}
				} else if (training instanceof VirtualAcademyTraining) {
					trainingList.addAll(dao.loadAllData(VirtualAcademyTraining.class));
					lastEntry = trainingList.get(trainingList.size() - 1);
					for (Employee e : employees) {
						EmployeeVirtualAcademy virtual = new EmployeeVirtualAcademy();
						virtual.setTraining(lastEntry);
						virtual.setEmployee(e);
						dao.add(virtual);
					}
				}
			}
			closeAddTrainingWindow();
		}
	}

	@FXML
	private void closeAddTrainingWindow() {
		Stage stage = (Stage) addTrainingBackButton.getScene().getWindow();
		stage.close();
	}
}
