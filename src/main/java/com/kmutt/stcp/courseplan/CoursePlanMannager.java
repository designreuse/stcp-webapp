package com.kmutt.stcp.courseplan;

import java.util.List;
import java.util.ArrayList;
import com.kmutt.stcp.entity.*;

public class CoursePlanMannager {

	// Field//
	private Account student;
	private List<CoursePlan> coursePlan;

	// Constructor//
	public CoursePlanMannager(Account acount) {

		this.student = acount;
	}

	// Method//
	public List<CoursePlan> getCoursePlanList() {

		if (this.coursePlan != null) {
			// get plans from common entity module
		}

		return this.coursePlan;

	}

	public List<Course> getCourseList() {

		return new ArrayList<Course>();
	}

	public Boolean addSubject(int semeterYear, int semeterTerm, Course course) {

		return true;
	}

	public Boolean removeSubject(int semeterYear, int semeterTerm, Course course) {

		return true;
	}

	public Boolean savePlan() {

		return true;
	}

}
