package com.kmutt.stcp.dto;

public class PlanMessageRequest {

	private int semesterId;
	private int semesterYear;
	private int semesterTerm;
	private int subjectId;

	public int getSemesterId() {
		return semesterId;
	}
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public int getSemesterYear() {
		return semesterYear;
	}
	public void setSemesterYear(int value) {
		this.semesterYear = value;
	}
	
	public int getSemesterTerm() {
		return semesterTerm;
	}
	public void setSemesterTerm(int value) {
		this.semesterTerm = value;
	}
	
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int value) {
		this.subjectId = value;
	}
	
}
