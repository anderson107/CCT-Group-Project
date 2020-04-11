package com.saturn;

import java.util.List;

import com.controller.Dao;
import com.saturn.dao.DataSource;
import com.saturn.model.employee.Employee;

public class NewMain {

	public static void main(String[] args) {
	DataSource data = DataSource.getInstance();
	data.getFactory();
	Main.main(args);
	
	
	}

}
