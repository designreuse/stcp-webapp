package com.kmutt.stcp.web.report;

import com.kmutt.stcp.web.report.DBInterface;
import com.kmutt.stcp.web.report.ReportType;

import java.util.Map;

public class ReportMaster implements DBInterface {

	private Integer reportId;

	private String reportName;

	private ReportType reportType;

	private byte[] reportTemplate;

	public void prepareReport(Integer reportId) {
		//TODO read report file
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
