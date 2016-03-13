package com.kmutt.stcp.web.report;

import com.kmutt.stcp.web.report.DBInterface;
import com.kmutt.stcp.web.report.ReportType;
import net.sf.jasperreports.engine.*;

import java.util.HashMap;
import java.util.Map;

public class ReportMaster implements DBInterface {

	private Integer reportId;

	private String reportName;

	private ReportType reportType;

	private byte[] reportTemplate;

	public void prepareReport(Integer reportId) {
		//TODO read report file

try {
	// Compile jrxml file.
	JasperReport jasperReport = JasperCompileManager.compileReport("C:/Users/Gift/JaspersoftWorkspace/MyReports/Blank_A4.jrxml");

	// Parameters for report
	Map<String, Object> parameters = new HashMap<String, Object>();

	// DataSource
	// This is simple example, no database.
	// then using empty datasource.
	JRDataSource dataSource = new JREmptyDataSource();

	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

	// Make sure the output directory exists.
//		File outDir = new File("C:/jasperoutput");
//		outDir.mkdirs();

	// Export to PDF.
	JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Gift/Desktop/Blank_A4.pdf");

	System.out.println("Done!");
} catch (Exception e) {
e.printStackTrace();
	}


//WEB

		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		/*
		 * JasperPrint jasperPrint = null; try { String reportFileName =
		 * "Blank_A4.jrxml"; String reportPath =
		 * "C:/Users/Gift/JaspersoftWorkspace/MyReports/" + reportFileName; //
		 * String reportPath =
		 * "http://localhost:8080/JasperReportPlayer/Blank_A4.jrxml"; String
		 * targetFileName = reportFileName.replace(".jrxml", ".pdf");
		 *
		 * JasperReport jasperReport =
		 * JasperCompileManager.compileReport(reportPath); jasperPrint =
		 * JasperFillManager.fillReport(jasperReport, null); ServletOutputStream
		 * outputstream = response.getOutputStream(); ByteArrayOutputStream
		 * byteArrayOutputStream = new ByteArrayOutputStream();
		 * JasperExportManager.exportReportToPdfStream(jasperPrint,
		 * byteArrayOutputStream); response.setContentType("application/pdf");
		 * outputstream.write(byteArrayOutputStream.toByteArray());
		 * response.setHeader("Cache-Control", "max-age=0");
		 * response.setHeader("Content-Disposition", "attachment; filename=" +
		 * targetFileName); outputstream.flush(); outputstream.close(); } catch
		 * (JRException e) { e.printStackTrace(); }
		 */

	/*	ServletOutputStream servletOutputStream = response.getOutputStream();
		File reportFile = new File(getServletConfig().getServletContext().getRealPath("C:/Users/Gift/JaspersoftWorkspace/MyReports/Blank_A4.jrxml"));
		byte[] bytes = null;

		try {
			bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), new HashMap(), new JREmptyDataSource());

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);

			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (JRException e) {
			e.printStackTrace();
		}*/

//		try {
//
//			InputStream inputStream = new FileInputStream ("C:/Users/Gift/JaspersoftWorkspace/MyReports/Blank_A4.jrxml");
//
////			DataBeanMaker dataBeanMaker = new DataBeanMaker();
////			ArrayList<databean> dataBeanList = dataBeanMaker.getDataBeanList();
////
////			JRBeanCollectionDataSource beanColDataSource = new
////			JRBeanCollectionDataSource(dataBeanList);
//
//			Map parameters = new HashMap();
//
//			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//			JasperExportManager.exportReportToPdfFile(jasperPrint, "c:/reports/test_jasper.pdf");
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}


	}

	public void generateReport(Integer reportId, Map.Entry<String,Object> paramValues) {
		//TODO passing parameters name & value to create PDF
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public byte[] getReportTemplate() {
		return reportTemplate;
	}

	public void setReportTemplate(byte[] reportTemplate) {
		this.reportTemplate = reportTemplate;
	}
}
