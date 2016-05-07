package com.kmutt.stcp.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kmutt.stcp.service.CoursePlannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.kmutt.stcp.entity.*;
import org.springframework.stereotype.Component;

@Component("courseManager")
@Scope("prototype")
public class CourseManager {

	// Field//
	private final Logger logger = LoggerFactory.getLogger(CourseManager.class);

	@Autowired
	private CoursePlannerService coursePlannerService;

	private List<Subject> subjectList;
	private Account student;

	// Properties //
	public Account getStudent() {
		return student;
	}

	public void setStudent(Account value) {
		this.student = value;
	}

	// Constructor//
	public CourseManager() {
	}

	public CourseManager(Account student) {
		this.student = student;
	}

	// Method//
	public List<Subject> getSubjectList() {

		// TODO: should get subjectList from Common Entity module.
		this.subjectList = this.coursePlannerService.getSubjectListTest();
		//this.subjectList = this.dummySubjectList(this.student);
		return this.subjectList;

	}

	public Subject getSubjectByCode(String code) {

		if (this.subjectList == null) {
			this.subjectList = this.getSubjectList();
		}

		Subject resultSubject;

		try {

			resultSubject = this.subjectList.stream()
					.filter(subject -> subject.getSubjectCode().toLowerCase().equals(code.toLowerCase())).findFirst()
					.orElse(null);

		} catch (Exception e) {

			logger.error("Method:getSubjectByCode|Err:" + e.getMessage());
			resultSubject = null;

		}

		return resultSubject;

	}

	public Subject getSubjectByID(int id) {

		if (this.subjectList == null) {
			this.subjectList = this.getSubjectList();
		}

		Subject resultSubject;

		try {

			resultSubject = this.subjectList.stream().filter(subject -> subject.getId() == id).findFirst().orElse(null);

		} catch (Exception e) {

			logger.error("Method:getSubjectByID|Err:" + e.getMessage());
			resultSubject = null;
		}

		return resultSubject;

	}

	public List<Subject> searchSubject(String criteria) {

		if (this.subjectList == null) {
			this.subjectList = this.getSubjectList();
		}

		List<Subject> resultSubject = new ArrayList<Subject>();

		try {

			if (criteria != null && !criteria.isEmpty()) {

				// Should change this to get from Common Entity module.
				// TODO: resultSubject =
				// this.subjectRepository.findAll().stream()
				resultSubject = this.subjectList.stream().filter(subject -> {
					if (subject.getSubjectCode().toLowerCase().contains(criteria.toLowerCase())
							|| subject.getNameThai().toLowerCase().contains(criteria.toLowerCase())) {
						return true;
					} else {
						return false;
					}
				}).collect(Collectors.toList());
			} else {

				resultSubject = this.subjectList;

			}

		} catch (Exception e) {

			logger.error("Method:searchSubject|Err:" + e.getMessage());
			resultSubject = new ArrayList<Subject>();

		}

		return resultSubject;

	}

}
