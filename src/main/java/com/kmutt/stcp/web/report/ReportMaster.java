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

	}

	public void generateReport(Integer reportId, Map.Entry<String,Object> paramValues) {

	}

}
