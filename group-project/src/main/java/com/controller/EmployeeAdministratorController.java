package com.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.saturn.Main;
import com.saturn.dao.DatabaseConnection;
import com.saturn.model.employee.Employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EmployeeAdministratorController implements Initializable {

	@FXML
	private TableView<Employee> tableView;

	@FXML
	private TableColumn<Employee, String> checkbox;

	@FXML
	private TableColumn<Employee, Integer> id;

	@FXML
	private TableColumn<Employee, String> firstName;

	@FXML
	private TableColumn<Employee, String> lastName;

	@FXML
	private TableColumn<Employee, String> email;

	@FXML
	private TableColumn<Employee, LocalDate> dob;

	@FXML
	private TableColumn<Employee, String> mobile;

	@FXML
	private TableColumn<Employee, String> address;

	@FXML
	private TableColumn<Employee, String> city;

	@FXML
	private TableColumn<Employee, LocalDate> registered;

	private List<Employee> employeeList = new ArrayList<>();

	protected static ObservableList<Employee> selected = FXCollections.observableArrayList();;

	public EmployeeAdministratorController() {
	}

	// this method populates the table in the employee administrator
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		checkbox.setCellValueFactory(new PropertyValueFactory<Employee, String>("checkbox"));
		id.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
		firstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
		dob.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("DOB"));
		mobile.setCellValueFactory(new PropertyValueFactory<Employee, String>("mobile"));
		address.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
		city.setCellValueFactory(new PropertyValueFactory<Employee, String>("city"));
		registered.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("creationDate"));

		employeeList.addAll(DatabaseConnection.loadAllData(Employee.class));

		// it sets all the check boxes that are not in the database
		for (Employee e : employeeList) {
			e.setCheckbox(new CheckBox());
		}

		tableView.getItems().setAll(employeeList);
	}

	// this method deletes employee from the database
	@FXML
	private void deleteEmployee() {

		ObservableList<Employee> delete = FXCollections.observableArrayList();
		for (Employee e : employeeList) {
			if (e.getCheckbox().isSelected()) {
				delete.add(e);
			}
		}

		if (!delete.isEmpty()) {
			// Confirmation window of delete item and return if "no" is clicked
			int index = JOptionPane.showConfirmDialog(null, "Delete the selected item(s)?", "Confirmation Window",
					JOptionPane.YES_NO_OPTION);
			if (index == 1) {
				return;
			} else {
				for (Employee e : delete) {
					DatabaseConnection.delete(e);
				}
			}
		}

		employeeList.removeAll(delete);
		tableView.getItems().setAll(employeeList);

	}

	private void refresh() {
		employeeList.clear();
		employeeList.addAll(DatabaseConnection.loadAllData(Employee.class));
		for (Employee e : employeeList) {
			e.setCheckbox(new CheckBox());
		}
		tableView.getItems().setAll(employeeList);
	}

	@FXML
	private void openUpdateWindow() {
		Stage stage = null;

		for (Employee e : employeeList) {
			if (e.getCheckbox().isSelected()) {
				selected.add(e);
			}
		}

		if (selected.size() > 1 || selected.size() == 0) {
			JOptionPane.showMessageDialog(null, "Select only 1 employee to be updated");
			return;

		} else {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Views.UPDATE_EMPLOYEE));
				Parent root1 = (Parent) fxmlLoader.load();
				stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner(Main.stage);
				stage.setTitle("UPDATE EMPLOYEE");
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		stage.setOnHidden((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				refresh();
			}
		});
	}
}
