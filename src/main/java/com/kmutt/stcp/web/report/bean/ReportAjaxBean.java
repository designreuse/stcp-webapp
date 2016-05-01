package com.kmutt.stcp.web.report.bean;

import java.io.Serializable;

/**
 * Created by Gift on 23-Apr-16.
 */
public class ReportAjaxBean implements Serializable {
    private Integer reportId;
    private String curriculumYear;
    private String curriculumName;

    private Integer curriculumId;
    private String errorMsg;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getCurriculumYear() {
        return curriculumYear;
    }

    public void setCurriculumYear(String curriculumYear) {
        this.curriculumYear = curriculumYear;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
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
