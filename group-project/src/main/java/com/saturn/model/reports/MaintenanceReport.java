package com.saturn.model.reports;

import java.util.List;

public class MaintenanceReport extends ReportSuperClass {
	
	public <T> MaintenanceReport(List<T> list, String frameTitle,String outputFile, String sourceFile) {
		super(list, frameTitle, outputFile, sourceFile);
		
	}

}
