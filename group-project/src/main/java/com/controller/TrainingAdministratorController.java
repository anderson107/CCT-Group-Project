package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.ChecklistCategory;
import com.saturn.model.ChecklistSuperClass;
import com.saturn.model.FireWarden;
import com.saturn.model.HSETraining;
import com.saturn.model.HealthSafetyChecklist;
import com.saturn.model.SeaChangeTraining;
import com.saturn.model.Task;
import com.saturn.model.TrainingSuperClass;
import com.saturn.model.VirtualAcademyTraining;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TrainingAdministratorController implements Initializable {

	@FXML
	private TableView<TrainingSuperClass> tableView;

	@FXML
	private TableColumn<TrainingSuperClass, String> checkbox;

	@FXML
	private TableColumn<TrainingSuperClass, Integer> id;

	@FXML
	private TableColumn<TrainingSuperClass, String> description;

	@FXML
	private TableColumn<TrainingSuperClass, String> type;

	@FXML
	private TableColumn<TrainingSuperClass, String> status;

	@FXML
	private TableColumn<TrainingSuperClass, LocalDate> creationDate;

	@FXML
	private ChoiceBox<String> trainingAdministratorChoicebox;

	private List<TrainingSuperClass> trainingList = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("SeaChange", "HSE", "Virtual Academy", "All");

		// populate the Choice box;
		trainingAdministratorChoicebox.setItems(list);
		trainingAdministratorChoicebox.setValue("SeaChange");

		checkbox.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, String>("checkbox"));
		id.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, Integer>("id"));
		description.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, String>("training"));
		type.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, String>("className"));
		status.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, String>("status"));
		creationDate.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, LocalDate>("creationDate"));

		trainingList.addAll(DatabaseConnection.loadAllData(SeaChangeTraining.class));
		trainingList.addAll(DatabaseConnection.loadAllData(HSETraining.class));
		trainingList.addAll(DatabaseConnection.loadAllData(VirtualAcademyTraining.class));
		
		for (TrainingSuperClass tsc : trainingList) {
			tsc.setCheckbox(new CheckBox());
			String className = tsc.getClass().getSimpleName();
			System.out.println(className);
			switch (className) {
			case "SeaChangeTraining":
				tsc.setClassName("SeaChange");
				break;
			case "VirtualAcademyTraining":
				tsc.setClassName("Virtual Academy");
				break;
			case "HSETraining":
				tsc.setClassName("HSE");
				break;
			}
		}

		tableView.getItems().setAll(trainingList);

	}
}
