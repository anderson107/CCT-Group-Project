package com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import com.saturn.model.Validation;
import com.saturn.model.checklists.ChecklistSuperClass;
import com.saturn.model.checklists.CoffeeHACCP;
import com.saturn.model.checklists.DeliHACCP;
import com.saturn.model.checklists.FireWarden;
import com.saturn.model.checklists.FloorHACCP;
import com.saturn.model.checklists.HealthSafetyChecklist;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

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

	private List<ChecklistSuperClass> checkList;
	
	@FXML
	private TextField searchTextField;
	
	@FXML
	private Label searchLabel;

	// it initialises the table view with data from the database
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		id.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, Integer>("id"));
		description.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("itemDescription"));
		frequency.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("frequency"));
		type.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, String>("className"));
		select.setCellValueFactory(new PropertyValueFactory<ChecklistSuperClass, CheckBox>("checkbox"));

		checkList = loadData();

		tableView.getItems().setAll(checkList);
	}

	// it updates the checklist objects from pending to done
	@FXML
	private void updateChecklist() {

		Dao dao = new Dao();

		List<ChecklistSuperClass> selected = new ArrayList<>();

		for (ChecklistSuperClass selec : checkList) {

			if (selec.getCheckbox().isSelected()) {
				selected.add(selec);
			}
		}

		dao.updateChecklist(selected);

		checkList = loadData();

		tableView.getItems().setAll(checkList);
		
		searchTextField.setText("");
	}

	// it loads all checklist objects data from the database 
	private List<ChecklistSuperClass> loadData() {
		Dao dao = new Dao();
		List<ChecklistSuperClass> list = new ArrayList<>();
		list.addAll(dao.loadChecklistItems());
		
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
	
	// it validates the user input and reload the table view with the text field search input
	@FXML
	private void searchItem() {
		
		boolean search = Validation.isNumber(searchTextField, searchLabel, "Only Numbers");
		
		if(search) {
			int id = 0;
			String text = searchTextField.getText();
			id = Integer.parseInt(text);
			
			if(id==0) {
				return;
			}
			
			checkList = loadData();
			ListIterator<ChecklistSuperClass> iterator = checkList.listIterator();
			
			while(iterator.hasNext()) {
				ChecklistSuperClass item = iterator.next();
				
				if(item.getId() != id) {
					iterator.remove();
				}
			}
			
			tableView.getItems().setAll(checkList);
			
			searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			    @Override
			    public void handle(KeyEvent event) {
			    	checkList = loadData();
			    	tableView.getItems().setAll(checkList);
			    }
			});
		}
	}
}
