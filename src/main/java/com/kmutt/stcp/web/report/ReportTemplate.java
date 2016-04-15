package com.kmutt.stcp.web.report;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * Created by Gift on 03-Apr-16.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ReportTemplate {

    COURSE_OPENING("รายงานผลการวางแผนการเรียนของนักศึกษาแต่ละคน", "Course_Opening.jrxml"),
    STUDENT_PLANNING("รายงานผลรวมการวางแผนการเรียนของนักศึกษา", "Student_Planning.jrxml"),
    SUMMARY_PLANNING("รายงานรายวิชาที่เปิดสอนในแต่ละเทอม", "Summary_Planning.jrxml"),
    COURSE_DESCRIPTION("รายงานคำอธิบายรายวิชา", "Course_Description.pdf");

    private String reportName;
    private String reportTemplateName;
    ReportTemplate(String reportName, String reportTemplateName) {
        this.reportName = reportName;
        this.reportTemplateName = reportTemplateName;
    }

    public String getReportName() {
        return this.reportName;
    }

    public String getReportTemplateName() {
        return this.reportTemplateName;
    }

}

