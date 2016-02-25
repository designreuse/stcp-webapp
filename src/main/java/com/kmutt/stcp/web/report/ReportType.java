package com.kmutt.stcp.web.report;

public enum ReportType {

	NORMAL("normal"),
	STAT("statistics");

	private String reportTypeName;
	ReportType(String reportTypeName) {
		this.reportTypeName = reportTypeName;
	}

}
