package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.saturn.model.Administrator;
import com.saturn.model.Validation;
import com.saturn.model.checklists.ChecklistSuperClass;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button signInButton;

	@FXML
	private TextField loginTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private Label emailLabel;

	@FXML
	private Label passwordLabel;

	@FXML
	private Label invalid;

	@FXML
	private void openMenu() {

		boolean login = Validation.isTextFieldEmpty(loginTextField, emailLabel, "Invalid Login");
		boolean password = Validation.isTextFieldEmpty(passwordTextField, passwordLabel, "Invalid password");

		if (login && password) {

			Dao dao = new Dao();
			Administrator adm = dao.getAdministrator(1);

			if (loginTextField.getText().matches(adm.getLogin())
					&& passwordTextField.getText().matches(adm.getPassword())) {

				List<ChecklistSuperClass> list = new ArrayList<>();
				
				list.addAll(dao.LoadChecklistItems());
				
				// it updates the checklist dates in the DB if the task wa done
				dao.updateDBChecklistDates(list);
				
				Stage stage = (Stage) signInButton.getScene().getWindow();
				stage.close();
				
				// it checks if there is any maintenance contract expired
				dao.checkMaintenance();
				
			} else {
				invalid.setText("Invalid email or password");
				return;
			}
		}

	}
}
