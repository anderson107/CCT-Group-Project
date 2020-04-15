package com.saturn;

import java.io.File;

import com.saturn.dao.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class NewMain {

	public static void main(String[] args) {
	
	DataSource data = DataSource.getInstance();
	data.getFactory();
	Main.main(args);
		
	}
}
