package com.saturn.model.reports;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.saturn.model.task.Task;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class TaskReport {
	
	public TaskReport(List<Task>list) {
		
		JFrame frame = new JFrame("Task report");

        JRViewer viewer = new JRViewer(generateReport(list));
        
        frame.add(viewer);
        frame.setSize(new Dimension(500, 600));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
	}

	public JasperPrint generateReport(List<Task>list) {
		
		String directory = System.getProperty("user.home");
		String ouptup = directory + File.separatorChar +"Downloads"+File.separatorChar+"TaskReport.pdf";
		String sourceFile = "src/main/resources/view/report/Task.jasper";
		JasperPrint jasperPrint = null;
		try {

			List<Task> tasklist = list;

			JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(tasklist);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ItemDataSource", itemsJRBean);

			
			jasperPrint = JasperFillManager.fillReport(sourceFile
					, parameters,
					new JREmptyDataSource());
			
			OutputStream outputStream = new FileOutputStream(new File(ouptup));
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			
			
		} catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
		return jasperPrint;
	}
}
