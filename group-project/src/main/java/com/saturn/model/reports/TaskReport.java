package com.saturn.model.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saturn.dao.DatabaseConnection;
import com.saturn.model.task.Task;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class TaskReport {
	
	public TaskReport(List<Task>list) {
		generateReport(list);
	}

	public void generateReport(List<Task>list) {
		
		String directory = "/Users/Dell/Desktop/cct";
		String ouptup = directory + File.separatorChar + "TaskReport.pdf";

		try {

			List<Task> tasklist = list;

			JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(tasklist);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ItemDataSource", itemsJRBean);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					"src/main/resources/Task.jasper", parameters,
					new JREmptyDataSource());
			
			OutputStream outputStream = new FileOutputStream(new File(ouptup));
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

			System.out.println("File Generated");
			
		} catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
	}
}
