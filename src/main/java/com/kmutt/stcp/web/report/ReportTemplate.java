package com.kmutt.stcp.web.report;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by Gift on 03-Apr-16.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReportTemplate {

    STUDENT_PLANNING("รายงานผลการวางแผนการเรียนของนักศึกษาแต่ละคน", "Student_Planning.jrxml"),
    SUMMARY_PLANNING("รายงานผลรวมการวางแผนการเรียนของนักศึกษา", "Summary_Planning.jrxml"),
    COURSE_OPENING("รายงานรายวิชาที่เปิดสอนในแต่ละเทอม", "Course_Opening.jrxml"),
    COURSE_DESCRIPTION("รายงานคำอธิบายรายวิชา", "Course_Description.pdf");

    private String reportName;
    private String reportTemplateName;
    ReportTemplate(String reportName, String reportTemplateName) {
        this.reportName = reportName;
        this.reportTemplateName = reportTemplateName;
    }

    public int getReportId() {
        return this.ordinal();
    }

    public String getReportName() {
        return this.reportName;
    }

    public String getReportTemplateName() {
        return this.reportTemplateName;
    }

}

