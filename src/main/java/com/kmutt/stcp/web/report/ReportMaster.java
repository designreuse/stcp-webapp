package com.kmutt.stcp.web.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReportMaster {

    private Integer reportId;

    private String reportName;

    private ReportType reportType;

    private byte[] reportTemplate;

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
