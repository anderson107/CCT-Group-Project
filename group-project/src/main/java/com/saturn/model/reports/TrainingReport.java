package com.saturn.model.reports;

import java.util.List;

public class TrainingReport extends ReportSuperClass {
	
	public <T> TrainingReport(List<T> list, String frameTitle,String outputFile, String sourceFile) {
		super(list, frameTitle, outputFile, sourceFile);
		
	}

}
