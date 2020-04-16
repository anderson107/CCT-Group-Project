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


import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class ReportSuperClass {	

	private JRBeanCollectionDataSource itemsBeans;
	
	public <T> ReportSuperClass(List<T> list, String frameTitle,String outputFile, String sourceFile) {

		JFrame frame = new JFrame(frameTitle);

		JRViewer viewer = new JRViewer(generateReport(list, outputFile, sourceFile));

		frame.add(viewer);
		frame.setSize(new Dimension(500, 600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	public <T> JasperPrint generateReport(List<T> list, String outputFile, String sourceFile) {

		String directory = System.getProperty("user.home");
		String ouptup = directory + File.separatorChar +"Downloads"+File.separatorChar+outputFile;
		String source = "src/main/resources/view/report/"+sourceFile;
		JasperPrint jasperPrint = null;
		
		try {

			itemsBeans = new JRBeanCollectionDataSource(list);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("ItemDataSource", itemsBeans);

			jasperPrint = JasperFillManager.fillReport(source, parameters, new JREmptyDataSource());

			OutputStream output = new FileOutputStream(new File(ouptup));

			JasperExportManager.exportReportToPdfStream(jasperPrint, output);

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		return jasperPrint;
	}
}
