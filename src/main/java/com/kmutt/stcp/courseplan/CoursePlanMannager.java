package com.kmutt.stcp.courseplan;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Console;
import java.util.ArrayList;
import com.kmutt.stcp.entity.*;
import com.kmutt.stcp.repository.*;

public class CoursePlanMannager {

	// Field//
	private final Logger logger = LoggerFactory.getLogger(CoursePlanMannager.class);

	@Autowired
	private CoursePlanRepository coursePlanRespository;

	private Account student;
	private ArrayList<CoursePlan> semesterPlanList;
	private ArrayList<CoursePlan> semesterPlanDeleted;

	// Constructor//
	public CoursePlanMannager(Account acount) {
		this.student = acount;
	}

	// Method//
	public ArrayList<CoursePlan> getCoursePlanList() {

		if (this.semesterPlanList == null) {
			// get plans from common entity module
			// this.semesterPlanList = (ArrayList<CoursePlan>)this.coursePlanRespository.findAll();
			this.semesterPlanList = getSemesterPlanListDummy();
		}

		return this.semesterPlanList;

	}

	public ArrayList<CoursePlan> getCoursePlanList(int semesterYear) {

		ArrayList<CoursePlan> coursePlanFilter = new ArrayList<>();

		// get plans from common entity module
		// ArrayList<CoursePlan> semesterList = (ArrayList<CoursePlan>)this.coursePlanRespository.findAll();
		ArrayList<CoursePlan> semesterList = this.getSemesterPlanListDummy();

		if (semesterList != null && !semesterList.isEmpty()) {

			for (CoursePlan semester : semesterList) {

				if (semester.getSemesterYear() == semesterYear)
					coursePlanFilter.add(semester);
			}
		}

		return coursePlanFilter;

	}

	public ArrayList<Subject> getSubjectSelectedList() {

		ArrayList<Subject> subjectSelectedList = new ArrayList<>();

		// get plans from common entity module.
		// ArrayList<CoursePlan> semesterList = (ArrayList<CoursePlan>)this.coursePlanRespository.findAll();
		ArrayList<CoursePlan> semesterList = this.getSemesterPlanListDummy();

		if (semesterList != null && !semesterList.isEmpty()) {

			for (CoursePlan semester : semesterList) {

				subjectSelectedList.add(semester.getSubject());
			}
		}

		return subjectSelectedList;

	}

	public ArrayList<Integer> getSemesterYearList() {

		ArrayList<Integer> semesterYearList = new ArrayList<>();

		// get plans from common entity module
		// ArrayList<CoursePlan> semesterList =
		// (ArrayList<CoursePlan>)this.coursePlanRespository.findAll();
		ArrayList<CoursePlan> semesterList = this.getSemesterPlanListDummy();

		for (CoursePlan coursePlan : semesterList) {

			Boolean isFound = false;

			for (Integer year : semesterYearList) {
				
				if (coursePlan.getSemesterYear().equals(year)) {
					isFound = true;
					break;
				}
			}

			if (!isFound)
				semesterYearList.add(coursePlan.getSemesterYear());

		}

		return semesterYearList;

	}
	
	public Boolean addSubject(int semesterYear, int semesterTerm, int subjectId) {

		try {

			Subject subjectSelected = (new CourseManager(this.student)).getSubjectByID(subjectId);
			return addSubject(semesterYear, semesterTerm, subjectSelected);

		} catch (Exception e) {

			logger.error("Method:addSubject(by Subject ID)|Err:" + e.getMessage());

			return false;

		}
	}

	public Boolean addSubject(int semesterYear, int semesterTerm, Subject subjectSelected) {

		try {

			// should get semesterList from Common Entity module.
			ArrayList<CoursePlan> semesterList = this.getSemesterPlanListDummy();

			CoursePlan newCoursePlan = new CoursePlan();

			newCoursePlan.setAccount(this.student);
			newCoursePlan.setSemesterYear(semesterYear);
			newCoursePlan.setSemesterTerm(semesterTerm);
			newCoursePlan.setSubject(subjectSelected);

			semesterList.add(newCoursePlan);

			return true;

		} catch (Exception e) {

			logger.error("Method:addSubject(by Subject)|Err:" + e.getMessage());

			return false;
		}
	}

	public Boolean removeSubject(int subjectId) {

		ArrayList<CoursePlan> semesterList = this.getSemesterPlanListDummy();

		if (this.semesterPlanDeleted == null)
			this.semesterPlanDeleted = new ArrayList<>();

		try {

			if (semesterList != null && !semesterList.isEmpty()) {

				for (CoursePlan semester : semesterList) {

					Subject subject = semester.getSubject();

					if (subject.getId() == subjectId) {
						semesterList.remove(semester);
						semesterPlanDeleted.add(semester);
						break;
					}
				}
			}

			return true;

		} catch (Exception e) {

			logger.error("Method:removeSubject|Err:" + e.getMessage());

			return false;
		}
	}

	public Boolean savePlan() {

		try {

			// Delete semesters before add or update.
			for (CoursePlan semesterDeleted : semesterPlanDeleted) {

				coursePlanRespository.delete(semesterDeleted);
			}

			for (CoursePlan semester : semesterPlanList) {

				if (coursePlanRespository.findOne(semester.getId()) != null) {

					coursePlanRespository.update(semester);

				} else {

					coursePlanRespository.create(semester);
				}
			}

			return true;

		} catch (Exception e) {

			logger.error("Method:savePlan|Err:" + e.getMessage());

			return false;

		}
	}

	// dummy//
	public ArrayList<CoursePlan> getSemesterPlanListDummy() {
		
		CourseManager courseMng = new CourseManager(null);

		ArrayList<CoursePlan> semesterList = new ArrayList<>();

		/* Semester#01/2015 */
		CoursePlan plan1 = new CoursePlan();
		plan1.setAccount(this.student);
		plan1.setId(1);
		plan1.setSemesterYear(2015);
		plan1.setSemesterTerm(1);
		plan1.setSubject(courseMng.getSubjectByCode("SWE 601"));
		semesterList.add(plan1);

		CoursePlan plan2 = new CoursePlan();
		plan2.setAccount(this.student);
		plan2.setId(2);
		plan2.setSemesterYear(2015);
		plan2.setSemesterTerm(1);
		plan2.setSubject(courseMng.getSubjectByCode("SWE 602"));
		semesterList.add(plan2);

		CoursePlan plan3 = new CoursePlan();
		plan3.setAccount(this.student);
		plan3.setId(3);
		plan3.setSemesterYear(2015);
		plan3.setSemesterTerm(1);
		plan3.setSubject(courseMng.getSubjectByCode("SWE 603"));
		semesterList.add(plan3);

		/* Semester#02/2015 */
		CoursePlan plan4 = new CoursePlan();
		plan4.setAccount(this.student);
		plan4.setId(4);
		plan4.setSemesterYear(2015);
		plan4.setSemesterTerm(2);
		plan4.setSubject(courseMng.getSubjectByCode("SWE 604"));
		semesterList.add(plan4);

		CoursePlan plan5 = new CoursePlan();
		plan5.setAccount(this.student);
		plan5.setId(5);
		plan5.setSemesterYear(2015);
		plan5.setSemesterTerm(2);
		plan5.setSubject(courseMng.getSubjectByCode("SWE 605"));
		semesterList.add(plan5);

		CoursePlan plan6 = new CoursePlan();
		plan6.setAccount(this.student);
		plan6.setId(6);
		plan6.setSemesterYear(2015);
		plan6.setSemesterTerm(2);
		plan6.setSubject(courseMng.getSubjectByCode("SWE 630"));
		semesterList.add(plan6);

		/* Semester#01/2016 */
		CoursePlan plan7 = new CoursePlan();
		plan7.setAccount(this.student);
		plan7.setId(7);
		plan7.setSemesterYear(2016);
		plan7.setSemesterTerm(1);
		plan7.setSubject(courseMng.getSubjectByCode("SWE 612"));
		semesterList.add(plan7);

		CoursePlan plan8 = new CoursePlan();
		plan8.setAccount(this.student);
		plan8.setId(8);
		plan8.setSemesterYear(2016);
		plan8.setSemesterTerm(1);
		plan8.setSubject(courseMng.getSubjectByCode("SWE 650"));
		semesterList.add(plan8);

		CoursePlan plan9 = new CoursePlan();
		plan9.setAccount(this.student);
		plan9.setId(9);
		plan9.setSemesterYear(2016);
		plan9.setSemesterTerm(1);
		plan9.setSubject(courseMng.getSubjectByCode("INT 678"));
		semesterList.add(plan9);

		/* Semester#02/2016 */
		CoursePlan plan10 = new CoursePlan();
		plan10.setAccount(this.student);
		plan10.setId(10);
		plan10.setSemesterYear(2016);
		plan10.setSemesterTerm(2);
		plan10.setSubject(courseMng.getSubjectByCode("SWE 610"));
		semesterList.add(plan10);

		CoursePlan plan11 = new CoursePlan();
		plan11.setAccount(this.student);
		plan11.setId(11);
		plan11.setSemesterYear(2016);
		plan11.setSemesterTerm(2);
		plan11.setSubject(courseMng.getSubjectByCode("SWE 651"));
		semesterList.add(plan11);

		CoursePlan plan12 = new CoursePlan();
		plan12.setAccount(this.student);
		plan12.setId(12);
		plan12.setSemesterYear(2016);
		plan12.setSemesterTerm(2);
		plan12.setSubject(courseMng.getSubjectByCode("INT 675"));
		semesterList.add(plan12);

		return semesterList;

	}
}
