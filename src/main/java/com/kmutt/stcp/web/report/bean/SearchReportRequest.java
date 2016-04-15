package com.kmutt.stcp.web.report.bean;

import java.io.Serializable;

/**
 * Created by Gift on 12-Apr-16.
 */
public class SearchReportRequest implements Serializable {
    private String reportId;
    private String reportName;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}
