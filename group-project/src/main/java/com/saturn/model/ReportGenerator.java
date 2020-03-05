package com.saturn.model;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

public class ReportGenerator {
	
	JasperReportBuilder report = DynamicReports.report();

	public ReportGenerator() {
		
		Connection connection = null;
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-04-one-to-many-uni?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		
		
		try {
			
			System.out.println("Connecting to database" + jdbcUrl);
			connection = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection successful!!!");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		report.columns(
			Columns.column("Employee id", "id", DataTypes.integerType()).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
			Columns.column("First Name","first_name", DataTypes.stringType()),
			Columns.column("Last Name","last_name", DataTypes.stringType()),	
			Columns.column("Email","email", DataTypes.stringType()))
		.title(
			Components.text("Employee Report").setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
			)
			.pageFooter(Components.pageXofY())	
			.setDataSource("SELECT id, first_name, last_name, email FROM instructor", 
                    connection);
				
	}
	
	public void show() {
		try {
			report.show();
			//report.toPdf(new FileOutputStream("C:\\Users\\DELL\\Desktop\\cct\\CA3"));
			
		}catch(Exception e) {e.printStackTrace();}
	}
}
