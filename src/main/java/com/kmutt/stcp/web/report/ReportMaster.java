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

public class ReportMaster implements DBInterface {

    private Integer reportId;

    private String reportName;

    private ReportType reportType;

    private byte[] reportTemplate;

    /**
     * @deprecated combined with generateReport
     * @param reportId
     */
    public void prepareReport(Integer reportId) {
        //TODO read report file


    }

    public void generateReport(Integer reportId, Map.Entry<String, Object> paramValues) {
        //TODO passing parameters name & value to create PDF

        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("report-templates/Course_Opening.jrxml");
            JasperDesign jrDesign = JRXmlLoader.load(in);

            // Compile jrxml file.
            JasperReport jasperReport = JasperCompileManager.compileReport(jrDesign);

            // Parameters for report
            Map<String, Object> parameters = new HashMap<>();

            // DataSource
            // This is simple example, no database.
            // then using empty datasource.
            JRDataSource dataSource = new JREmptyDataSource();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            reportTemplate = JasperExportManager.exportReportToPdf(jasperPrint);

            System.out.println("Done!");

        } catch (JRException e) {
            e.printStackTrace();
        }

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
