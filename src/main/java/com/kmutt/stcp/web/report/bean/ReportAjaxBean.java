package com.kmutt.stcp.web.report.bean;

import java.io.Serializable;

/**
 * Created by Gift on 23-Apr-16.
 */
public class ReportAjaxBean implements Serializable {
    private Integer reportId;
    private Integer curriculumId;

    private String errorMsg;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Integer curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
