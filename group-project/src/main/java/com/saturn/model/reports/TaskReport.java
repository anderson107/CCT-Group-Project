package com.saturn.model.reports;

import java.util.List;


public class TaskReport extends ReportSuperClass {
	
	public <T> TaskReport(List<T> list, String frameTitle,String outputFile, String sourceFile) {
		super(list, frameTitle, outputFile, sourceFile);
		
	}

	
}
