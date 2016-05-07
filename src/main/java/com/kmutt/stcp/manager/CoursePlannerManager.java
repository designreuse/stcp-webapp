package com.kmutt.stcp.manager;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kmutt.stcp.entity.*;
import com.kmutt.stcp.repository.CoursePlanRepository;
import com.kmutt.stcp.dto.PlanMessageRequest;
import com.kmutt.stcp.service.CoursePlannerService;

import org.springframework.stereotype.Component;

@Component("coursePlanManager")
@Scope("prototype")
public class CoursePlannerManager {

    // Field//
    private final Logger logger = LoggerFactory.getLogger(CoursePlannerManager.class);

    @Autowired
    ApplicationContext appContext;

    @Autowired
    private CoursePlannerService coursePlanService;

    @Autowired
    private CoursePlanRepository coursePlanRespository;

    private Account student;
    private List<CoursePlan> semesterPlanList;
    private List<CoursePlan> semesterPlanNew;
    private List<CoursePlan> semesterPlanDeleted;

    // Constructor//
    public CoursePlannerManager() {
    }

    public CoursePlannerManager(Account acount) {
        this.student = acount;
    }

    // Properties //
    public Account getStudent() {
        return student;
    }

    public void setStudent(Account value) {
        this.student = value;
    }

    // Method//
    public List<CoursePlan> getCoursePlanList() {

        this.semesterPlanList = this.coursePlanService.getCoursePlan(this.student);
        return this.semesterPlanList;

    }

    public List<CoursePlan> getCoursePlanList(int semesterYear) {

        List<CoursePlan> coursePlanFilter = this.coursePlanService.getCoursePlan(this.student, semesterYear);
        return coursePlanFilter;

    }

    public List<Subject> getSubjectSelectedList() {

        ArrayList<Subject> subjectSelectedList = new ArrayList<>();

        // TODO: get plans from common entity module.
        // List<CoursePlan> semesterList =
        // this.coursePlanRespository.querySQL("select * from course_plan where
        // account_id = " + this.student.getId().toString());
        // List<CoursePlan> semesterList = this.getSemesterPlanListDummy();
        List<CoursePlan> semesterList = this.getCoursePlanList();

        if (semesterList != null && !semesterList.isEmpty()) {

            for (CoursePlan semester : semesterList) {
                subjectSelectedList.add(semester.getSubject());
            }
        }

        return subjectSelectedList;

    }

    public List<Integer> getSemesterYearList() {

        List<Integer> semesterYearList = new ArrayList<>();

        List<CoursePlan> semesterList = this.getCoursePlanList();

        Integer currentYear = java.time.LocalDateTime.now().getYear();
        currentYear = currentYear >= 2116 ? currentYear - 543 : currentYear;

        Integer startYear = getStudentStartYear();

        if (semesterList != null) {

            for (CoursePlan coursePlan : semesterList) {

                Boolean isFound = false;

                Integer yearCompare = coursePlan.getSemesterYear();

                for (Integer year : semesterYearList) {

                    if (yearCompare.equals(year)) {
                        isFound = true;
                        break;
                    }

                }

                if (!isFound)
                    semesterYearList.add(yearCompare);

            }
        }

        if (semesterYearList.isEmpty()) {

//			if (startYear >= currentYear)
            semesterYearList.add(startYear);

        } else {

            semesterYearList.sort((c1, c2) -> c1.compareTo(c2));

            if (startYear < semesterYearList.get(0))// && startYear >= currentYear)
                semesterYearList.add(0, startYear);

        }

        if (semesterYearList.size() == 1)
            semesterYearList.add(semesterYearList.get(semesterYearList.size() - 1) + 1);

        return semesterYearList;

    }

    public Boolean setCoursePlanForSave(List<PlanMessageRequest> semesterObj) {

        try {

            if (semesterObj != null) {

                CourseManager courseMng = appContext.getBean(CourseManager.class);
                courseMng.setStudent(this.student);

                semesterPlanNew = new ArrayList<>();

                for (PlanMessageRequest obj : semesterObj) {

                    if (obj.getSemesterId() == 0) {

                        CoursePlan newCoursePlan = new CoursePlan();
                        newCoursePlan.setAccount(this.student);
                        newCoursePlan.setSemesterYear(obj.getSemesterYear());
                        newCoursePlan.setSemesterTerm(obj.getSemesterTerm());
                        newCoursePlan.setSubject(courseMng.getSubjectByID(obj.getSubjectId()));

                        semesterPlanNew.add(newCoursePlan);

                    }
                }

                semesterPlanDeleted = new ArrayList<>();

                List<CoursePlan> planList = this.getCoursePlanList();

                if (planList != null) {

                    for (CoursePlan coursePlan : planList) {

                        Boolean isDeleted = true;

                        for (PlanMessageRequest planMessageRequest : semesterObj) {

                            if (coursePlan.getId().equals(planMessageRequest.getSemesterId())) {

                                isDeleted = false;
                                break;

                            }
                        }

                        if (isDeleted) {
                            semesterPlanDeleted.add(coursePlan);
                        }

                    }
                }
            }

            return true;

        } catch (Exception e) {

            logger.error("Method:setCoursePlanForSave|Err:" + e.getMessage());

            semesterPlanNew = new ArrayList<>();
            semesterPlanDeleted = new ArrayList<>();

            return false;

        }
    }

    public Boolean savePlan() {

        try {

            // Delete semesters before insert new subject
            if (semesterPlanDeleted != null) {
                for (CoursePlan semesterDeleted : semesterPlanDeleted) {

                    // coursePlanService.delete(semesterDeleted);
                    this.coursePlanRespository.delete(semesterDeleted);

                }
            }

            // Insert new subject.
            if (semesterPlanNew != null) {
                for (CoursePlan coursePlan : semesterPlanNew) {

                    // coursePlanService.create(coursePlan);
                    this.coursePlanRespository.create(coursePlan);

                }
            }
            return true;

        } catch (Exception e) {

            logger.error("Method:savePlan|Err:" + e.getMessage());

            return false;

        } finally {

            semesterPlanNew = new ArrayList<>();
            semesterPlanDeleted = new ArrayList<>();

        }
    }

    private Integer getStudentStartYear() {

        Integer returnYear = java.time.LocalDateTime.now().getYear();
        returnYear = returnYear >= 2116 ? returnYear - 543 : returnYear;

        try {

            String yearStartStr = this.student.getUser().getCurriculum().getStartYear();
            Integer yearStart = 9999;

            if (yearStartStr != null && !yearStartStr.isEmpty() && NumberUtils.isNumber(yearStartStr)) {
                yearStart = Integer.parseInt(yearStartStr);
                yearStart = yearStart >= 2116 ? yearStart - 543 : yearStart;
            }

            String yearStartedStr = this.student.getUser().getCurriculum().getStartedYear();
            Integer yearStarted = 9999;

            if (yearStartedStr != null && !yearStartedStr.isEmpty() && NumberUtils.isNumber(yearStartedStr)) {
                yearStarted = Integer.parseInt(yearStartedStr);
                yearStarted = yearStarted >= 2116 ? yearStarted - 543 : yearStarted;
            }

            if (yearStart <= yearStarted) {
                returnYear = yearStart;
            } else {
                returnYear = yearStarted;
            }

        } catch (Exception e) {
        }

        return returnYear;

    }

}
