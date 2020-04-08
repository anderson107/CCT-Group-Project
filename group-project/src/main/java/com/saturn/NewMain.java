package com.saturn;

import java.util.ArrayList;
import java.util.List;

import com.saturn.dao.DataSource;
import com.saturn.model.employee.Employee;
import com.saturn.model.maintenance.Maintenance;

public class NewMain {

	public static void main(String[] args) {
	DataSource data = DataSource.getInstance();
	data.getFactory();
	
	Main.main(args);
	
	}

}
