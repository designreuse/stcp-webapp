package com.kmutt.stcp.manager;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.kmutt.stcp.dto.PlanMessageRequest;
import com.kmutt.stcp.entity.*;

public class CoursePlannerManagerTest {

	@Autowired
	private static CoursePlannerManager coursePlanMng;

	@BeforeClass
	public static void setUp() {

		Account student = new Account();
		student.setId(1);

		coursePlanMng = new CoursePlannerManager(student);

	}

	@Test
	public void getCoursePlanListAll() {

		List<CoursePlan> subjectList = coursePlanMng.getCoursePlanList();

		assertNotNull(subjectList);
		assertEquals(12, subjectList.size(), 0);

		for (CoursePlan coursePlan : subjectList) {

			assertTrue(coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 601")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 602")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 603")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 604")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 605")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 610")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 612")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 630")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 650")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 651")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("int 675")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("int 678"));

		}

	}

	@Test
	public void getCoursePlanListBySemesterYear() {

		List<CoursePlan> subjectList = coursePlanMng.getCoursePlanList(2016);

		assertNotNull(subjectList);
		assertEquals(6, subjectList.size(), 0);

		for (CoursePlan coursePlan : subjectList) {

			assertTrue(coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 610")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 612")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 650")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("swe 651")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("int 675")
					|| coursePlan.getSubject().getSubjectCode().toLowerCase().equals("int 678"));

		}

	}

	@Test
	public void getSemesterYearList() {

		List<Integer> yearList = coursePlanMng.getSemesterYearList();

		assertNotNull(yearList);
		assertEquals(2, yearList.size(), 0);

		for (Integer year : yearList) {
			assertTrue(year == 2015 || year == 2016);
		}

	}

	@Test
	public void getSubjectSelectedList() {

		List<Subject> subjectSelected = coursePlanMng.getSubjectSelectedList();

		assertNotNull(subjectSelected);
		assertEquals(12, subjectSelected.size(), 0);

		for (Subject subject : subjectSelected) {

			assertTrue(subject.getSubjectCode().toLowerCase().equals("swe 601")
					|| subject.getSubjectCode().toLowerCase().equals("swe 602")
					|| subject.getSubjectCode().toLowerCase().equals("swe 603")
					|| subject.getSubjectCode().toLowerCase().equals("swe 604")
					|| subject.getSubjectCode().toLowerCase().equals("swe 605")
					|| subject.getSubjectCode().toLowerCase().equals("swe 610")
					|| subject.getSubjectCode().toLowerCase().equals("swe 612")
					|| subject.getSubjectCode().toLowerCase().equals("swe 630")
					|| subject.getSubjectCode().toLowerCase().equals("swe 650")
					|| subject.getSubjectCode().toLowerCase().equals("swe 651")
					|| subject.getSubjectCode().toLowerCase().equals("int 675")
					|| subject.getSubjectCode().toLowerCase().equals("int 678"));

		}

	}

	@Test
	public void setCoursePlanForSave() {

		List<PlanMessageRequest> requestPlanSave = getPlanMessageDataTest();

		assertTrue(coursePlanMng.setCoursePlanForSave(requestPlanSave));

	}

	@Test
	public void savePlan() {

		List<PlanMessageRequest> requestPlanSave = getPlanMessageDataTest();
		
		coursePlanMng.setCoursePlanForSave(requestPlanSave);

		assertTrue(coursePlanMng.savePlan());

	}
	
	private List<PlanMessageRequest> getPlanMessageDataTest() {
		
		List<PlanMessageRequest> requestPlanSave = new ArrayList<>();
		
		PlanMessageRequest plan1 = new PlanMessageRequest();
		plan1.setSemesterYear(2015);
		plan1.setSemesterTerm(1);
		plan1.setSubjectId(30);
		requestPlanSave.add(plan1);

		PlanMessageRequest plan2 = new PlanMessageRequest();
		plan2.setSemesterYear(2015);
		plan2.setSemesterTerm(1);
		plan2.setSubjectId(31);
		requestPlanSave.add(plan2);

		PlanMessageRequest plan3 = new PlanMessageRequest();
		plan3.setSemesterYear(2015);
		plan3.setSemesterTerm(1);
		plan3.setSubjectId(32);
		requestPlanSave.add(plan3);
		
		PlanMessageRequest plan4 = new PlanMessageRequest();
		plan4.setSemesterYear(2015);
		plan4.setSemesterTerm(2);
		plan4.setSubjectId(33);
		requestPlanSave.add(plan4);

		PlanMessageRequest plan5 = new PlanMessageRequest();
		plan5.setSemesterYear(2015);
		plan5.setSemesterTerm(2);
		plan5.setSubjectId(34);
		requestPlanSave.add(plan5);

		PlanMessageRequest plan6 = new PlanMessageRequest();
		plan6.setSemesterYear(2015);
		plan6.setSemesterTerm(2);
		plan6.setSubjectId(43);
		requestPlanSave.add(plan6);

		PlanMessageRequest plan7 = new PlanMessageRequest();
		plan7.setSemesterYear(2016);
		plan7.setSemesterTerm(1);
		plan7.setSubjectId(37);
		requestPlanSave.add(plan7);

		PlanMessageRequest plan8 = new PlanMessageRequest();
		plan8.setSemesterYear(2016);
		plan8.setSemesterTerm(1);
		plan8.setSubjectId(50);
		requestPlanSave.add(plan8);

		PlanMessageRequest plan9 = new PlanMessageRequest();
		plan9.setSemesterYear(2016);
		plan9.setSemesterTerm(1);
		plan9.setSubjectId(29);
		requestPlanSave.add(plan9);
		
		PlanMessageRequest plan10 = new PlanMessageRequest();
		plan10.setSemesterYear(2016);
		plan10.setSemesterTerm(2);
		plan10.setSubjectId(35);
		requestPlanSave.add(plan10);

		PlanMessageRequest plan11 = new PlanMessageRequest();
		plan11.setSemesterYear(2016);
		plan11.setSemesterTerm(2);
		plan11.setSubjectId(54);
		requestPlanSave.add(plan11);

		PlanMessageRequest plan12 = new PlanMessageRequest();
		plan12.setSemesterYear(2016);
		plan12.setSemesterTerm(2);
		plan12.setSubjectId(26);
		requestPlanSave.add(plan12);
		
		return requestPlanSave;
		
	}
}
