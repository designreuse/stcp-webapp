package com.kmutt.stcp.web.report.bean;

import java.io.Serializable;

/**
 * Created by Gift on 23-Apr-16.
 */
public class ReportAjaxBean implements Serializable {
    private Integer reportId;
    private Integer courseId;

    private String errorMsg;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
