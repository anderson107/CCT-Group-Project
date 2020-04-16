package com.saturn.model.reports;

import java.util.List;

public class ChecklistReport extends ReportSuperClass {

	public <T> ChecklistReport(List<T> list, String frameTitle,String outputFile, String sourceFile) {
		super(list, frameTitle, outputFile, sourceFile);
		
	}
	
}
