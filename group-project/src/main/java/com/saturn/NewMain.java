package com.saturn;

import java.util.ArrayList;
import java.util.List;

import com.controller.MainMenuController;
import com.saturn.dao.DataSource;
import com.saturn.dao.DatabaseConnection;
import com.saturn.model.checklists.ChecklistSuperClass;
import com.saturn.model.checklists.CoffeeHACCP;
import com.saturn.model.checklists.DeliHACCP;
import com.saturn.model.checklists.FireWarden;
import com.saturn.model.checklists.FloorHACCP;
import com.saturn.model.checklists.HealthSafetyChecklist;
import com.saturn.model.employee.Employee;
import com.saturn.model.maintenance.Maintenance;
import com.saturn.model.reports.ChecklistReport;
import com.saturn.model.reports.TaskReport;

public class NewMain {

	public static void main(String[] args) {
	DataSource data = DataSource.getInstance();
	data.getFactory();
	Main.main(args);
	
	}
	
	
	

}
