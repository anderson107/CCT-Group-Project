package com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.saturn.model.Validation;
import com.saturn.model.maintenance.Maintenance;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UpdateContractorController implements Initializable {

	@FXML
	private Button backButton;

	@FXML
	private TextField textField;

	@FXML
	private TextArea textArea;

	@FXML
	private Label textFieldLabel;

	@FXML
	private Label textAreaLabel;

	@FXML
	private DatePicker lastDate;

	@FXML
	private DatePicker nextDate;

	private Maintenance maintenance;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Dao dataConnection = new Dao();
		maintenance = dataConnection.getMaintenance(MaintenanceTableController.selected.get(0).getId());
		textField.setText(maintenance.getCompany());
		textArea.setText(maintenance.getDescription());
		lastDate.setValue(maintenance.getLastDate());
		nextDate.setValue(maintenance.getNextDate());
	}

	// it updates the maintenance object in the database
	@FXML
	private void updateContractor() {
		Dao dataConnection = new Dao();

		boolean contractor = Validation.isTextFieldEmpty(textField, textFieldLabel, "Required");
		boolean service = Validation.isTextAreaEmpty(textArea, textAreaLabel, "Required");

		if (contractor && service) {
			dataConnection.updateMaintenance(maintenance.getId(), textField.getText(), textArea.getText(),
					lastDate.getValue(), nextDate.getValue());
			MaintenanceTableController.selected.clear();
			closeWindow();
		}
	}

	@FXML
	public void closeWindow() {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}
}
