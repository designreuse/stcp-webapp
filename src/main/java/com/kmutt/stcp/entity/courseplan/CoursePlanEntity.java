package com.kmutt.stcp.entity.courseplan;

import java.util.List;

public class CoursePlanEntity {

	//Field//
	private int studentId;
	private List<CoursePlanSemeterEntity> semeterPlanList;
	
		
	//Property//
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public List<CoursePlanSemeterEntity> getSemeterPlanList() {
		return semeterPlanList;
	}
	public void setSemeterPlanList(List<CoursePlanSemeterEntity> semeterPlanList) {
		this.semeterPlanList = semeterPlanList;
	}
	
	//Method//
	
}
