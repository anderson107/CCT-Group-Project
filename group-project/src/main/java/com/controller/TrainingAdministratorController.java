package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.saturn.Main;
import com.saturn.model.training.HSETraining;
import com.saturn.model.training.SeaChangeTraining;
import com.saturn.model.training.TrainingSuperClass;
import com.saturn.model.training.VirtualAcademyTraining;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
	private TableColumn<TrainingSuperClass, LocalDate> creationDate;

	@FXML
	private ChoiceBox<String> trainingAdministratorChoicebox;

	private List<TrainingSuperClass> trainingList = new ArrayList<>();

	protected static List<TrainingSuperClass> selected = new ArrayList<>();

	@FXML
	private Button backButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Dao dao = new Dao();
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("SeaChange", "HSE", "Virtual Academy", "All");

		// populate the Choice box;
		trainingAdministratorChoicebox.setItems(list);
		trainingAdministratorChoicebox.setValue("All");

		checkbox.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, String>("checkbox"));
		id.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, Integer>("id"));
		description.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, String>("training"));
		type.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, String>("className"));
		creationDate.setCellValueFactory(new PropertyValueFactory<TrainingSuperClass, LocalDate>("creationDate"));

		trainingList.addAll(dao.loadAllData(SeaChangeTraining.class));
		trainingList.addAll(dao.loadAllData(VirtualAcademyTraining.class));
		trainingList.addAll(dao.loadAllData(HSETraining.class));

		for (TrainingSuperClass tsc : trainingList) {
			tsc.setCheckbox(new CheckBox());
			
			if(tsc instanceof SeaChangeTraining) {
				tsc.setClassName("SeaChange");
			}else if(tsc instanceof VirtualAcademyTraining) {
				tsc.setClassName("Virtual Academy");
			}else if(tsc instanceof HSETraining) {
				tsc.setClassName("HSE");
			}
		}

		tableView.getItems().setAll(trainingList);

	}

	@FXML
	private void updateTrainingList() {
		Dao dao = new Dao();
		trainingList.clear();

		switch (trainingAdministratorChoicebox.getValue()) {

		case "SeaChange":
			trainingList.addAll(dao.loadAllData(SeaChangeTraining.class));
			for(TrainingSuperClass t: trainingList) {
				t.setClassName("SeaChange");
			}
			break;
		case "HSE":
			trainingList.addAll(dao.loadAllData(HSETraining.class));
			for(TrainingSuperClass t: trainingList) {
				t.setClassName("HSE");
			}
			break;
		case "Virtual Academy":
			trainingList.addAll(dao.loadAllData(VirtualAcademyTraining.class));
			for(TrainingSuperClass t: trainingList) {
				t.setClassName("Virtual Academy");
			}
			break;
		case "All":
			trainingList.addAll(dao.loadAllData(SeaChangeTraining.class));
			trainingList.addAll(dao.loadAllData(VirtualAcademyTraining.class));
			trainingList.addAll(dao.loadAllData(HSETraining.class));
			for(TrainingSuperClass t: trainingList) {
				
				if(t instanceof SeaChangeTraining) {
					t.setClassName("SeaChange");
				}else if(t instanceof VirtualAcademyTraining) {
					t.setClassName("Virtual Academy");
				}else if(t instanceof HSETraining) {
					t.setClassName("HSE");
				}
			}
			break;
		}

		for (TrainingSuperClass tsc : trainingList) {
			tsc.setCheckbox(new CheckBox());
		}

		tableView.getItems().setAll(trainingList);

	}

	@FXML
	private void removeTraining() {
		Dao dao = new Dao();
		ObservableList<TrainingSuperClass> delete = FXCollections.observableArrayList();
		for (TrainingSuperClass c : trainingList) {
			if (c.getCheckbox().isSelected()) {
				delete.add(c);
			}
		}

		if (!delete.isEmpty()) {
			// Confirmation window of delete item and return if "no" is clicked
			int index = JOptionPane.showConfirmDialog(null, "Delete the selected item(s)?", "Confirmation Window",
					JOptionPane.YES_NO_OPTION);
			if (index == 1) {
				return;
			} else {
				for (TrainingSuperClass d : delete) {
					dao.delete(d);
				}
			}
		}

		trainingList.removeAll(delete);
		tableView.getItems().setAll(trainingList);

	}

	private void refresh() {
		Dao dao = new Dao();
		trainingList.clear();
		trainingList.addAll(dao.loadAllData(SeaChangeTraining.class));
		trainingList.addAll(dao.loadAllData(VirtualAcademyTraining.class));
		trainingList.addAll(dao.loadAllData(HSETraining.class));

		for (TrainingSuperClass tsc : trainingList) {
			tsc.setCheckbox(new CheckBox());

			if(tsc instanceof SeaChangeTraining) {
				tsc.setClassName("SeaChange");
			}else if(tsc instanceof VirtualAcademyTraining) {
				tsc.setClassName("Virtual Academy");
			}else if(tsc instanceof HSETraining) {
				tsc.setClassName("HSE");
			}
		}
		tableView.getItems().setAll(trainingList);

	}

	@FXML
	private void openUpdateWindow() {
		Stage stage = null;
		int index = 0;
		for (TrainingSuperClass cl : trainingList) {
			if (cl.getCheckbox().isSelected()) {
				selected.add(cl);
				index++;
			}
		}
		if (index == 0 || index > 1) {
			JOptionPane.showMessageDialog(null, "Select 1 item");
			return;
		} else {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.UPDATE_TRAINING));
				Parent root1 = (Parent) fxmlLoader.load();
				stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner(Main.stage);
				stage.setTitle("Update Training");
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}

			stage.setOnHidden((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					refresh();
				}
			});
		}
	}
	
	@FXML
	private void closeWindow() {
		
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}
}
