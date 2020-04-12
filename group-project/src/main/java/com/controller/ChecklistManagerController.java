package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import com.saturn.model.checklists.ChecklistSuperClass;
import com.saturn.model.checklists.CoffeeHACCP;
import com.saturn.model.checklists.DeliHACCP;
import com.saturn.model.checklists.FireWarden;
import com.saturn.model.checklists.FloorHACCP;
import com.saturn.model.checklists.HealthSafetyChecklist;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChecklistManagerController implements Initializable {

	@FXML
	private TableView<ChecklistSuperClass> tableView;

	@FXML
	private TableColumn<ChecklistSuperClass, Integer> id;

	@FXML
	private TableColumn<ChecklistSuperClass, String> description;

	@FXML
	private TableColumn<ChecklistSuperClass, String> frequency;

	@FXML
	private TableColumn<ChecklistSuperClass, String> type;

	@FXML
	private TableColumn<ChecklistSuperClass, CheckBox> select;

	private List<ChecklistSuperClass> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		id.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, Integer>("id"));
		description.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("itemDescription"));
		frequency.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("frequency"));
		type.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("className"));
		select.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, CheckBox>("checkbox"));

		list = loadData();

		tableView.getItems().setAll(list);
	}

	@FXML
	private void updateChecklist() {

		Dao dao = new Dao();

		List<ChecklistSuperClass> selected = new ArrayList<>();

		for (ChecklistSuperClass selec : list) {

			if (selec.getCheckbox().isSelected()) {
				selected.add(selec);
			}
		}

		dao.updateChecklist(selected);

		list = loadData();

		tableView.getItems().setAll(list);
	}

	private List<ChecklistSuperClass> loadData() {
		Dao dao = new Dao();
		List<ChecklistSuperClass> list = new ArrayList<>();
		list.addAll(dao.loadAllData(FireWarden.class));
		list.addAll(dao.loadAllData(HealthSafetyChecklist.class));
		list.addAll(dao.loadAllData(DeliHACCP.class));
		list.addAll(dao.loadAllData(FloorHACCP.class));
		list.addAll(dao.loadAllData(CoffeeHACCP.class));
		
		ListIterator<ChecklistSuperClass> iterator = list.listIterator();

		while(iterator.hasNext()) {
			ChecklistSuperClass spc = iterator.next();
			
			if(spc.getStatus().matches("Done")) {
				iterator.remove();
			}
		}
		
		for (ChecklistSuperClass sp : list) {

			if (sp instanceof FireWarden) {
				sp.setClassName("Fire Warden");
			} else if (sp instanceof HealthSafetyChecklist) {
				sp.setClassName("Health and Safety");
			} else if (sp instanceof DeliHACCP) {
				sp.setClassName("Deli HACCP");
			} else if (sp instanceof FloorHACCP) {
				sp.setClassName("Floor HACCP");
			} else if (sp instanceof CoffeeHACCP) {
				sp.setClassName("Coffee HACCP");
			}
			
			sp.setCheckbox(new CheckBox());
		}
		return list;
	}
}
