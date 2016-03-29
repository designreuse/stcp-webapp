package com.kmutt.stcp.entity.courseplan;

import java.util.ArrayList;
import java.util.List;

import com.kmutt.stcp.entity.Course;

public class CoursePlanSemeterEntity {

	// Field //
	private int semeterPlanID;
	private int semeterPlanYear;
	private int semeterPlanPart;
	private List<Course> courseList;

	// Property //
	public int getSemeterPlanID() {
		return semeterPlanID;
	}

	public void setSemeterPlanID(int semeterPlanID) {
		this.semeterPlanID = semeterPlanID;
	}

	public int getSemeterPlanYear() {
		return semeterPlanYear;
	}

	public void setSemeterPlanYear(int semeterPlanYear) {
		this.semeterPlanYear = semeterPlanYear;
	}

	public int getSemeterPlanPart() {
		return semeterPlanPart;
	}

	public void setSemeterPlanPart(int semeterPlanPart) {
		this.semeterPlanPart = semeterPlanPart;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	// Constructor //
	public CoursePlanSemeterEntity() {


	}

	// Method //
	

}
