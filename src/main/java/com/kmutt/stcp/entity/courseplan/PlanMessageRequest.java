package com.kmutt.stcp.entity.courseplan;

public class PlanMessageRequest {
	
	private int semesterid;
	
	private int courseid;

	public int getSemesterid() {
		return semesterid;
	}

	public void setSemesterid(int value) {
		this.semesterid = value;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int value) {
		this.courseid = value;
	}


}
