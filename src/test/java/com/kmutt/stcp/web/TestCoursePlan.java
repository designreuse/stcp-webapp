package com.kmutt.stcp.web;

import static org.junit.Assert.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kmutt.stcp.config.SpringRootConfig;
import com.kmutt.stcp.config.SpringWebConfig;
import com.kmutt.stcp.entity.Account;
import com.kmutt.stcp.entity.CoursePlan;
import com.kmutt.stcp.entity.Subject;
import com.kmutt.stcp.manager.CourseManager;
import com.kmutt.stcp.manager.CoursePlannerManager;
import com.kmutt.stcp.repository.AccountRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class, SpringWebConfig.class, TestCoursePlan.class})
@WebAppConfiguration
public class TestCoursePlan {
	
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CoursePlannerManager coursePlan;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Before
    public void setup() {
		Account student = accountRepository.findOne(4);		
		coursePlan.setStudent(student);
    }

	@Test
	public void testMethod1() {
		
		List<CoursePlan> coursePlanList = coursePlan.getCoursePlanList();
		int expect = 1; 
		int actual = coursePlanList.size();
		String studentNameExpect = "teerapat";
		String studentNameActual = coursePlanList.get(0).getAccount().getUsername();
		
		Assert.assertEquals(expect, actual);	
		
		Assert.assertEquals(studentNameExpect, studentNameActual);
		
	}
	
	@Test
	public void testMethod2() {
		
		List<CoursePlan> coursePlanList = coursePlan.getCoursePlanList(2016); 
		
		int expect = 1; 
		
		int actual = coursePlanList.size();
		
		Assert.assertEquals(expect, actual);	
	}
	
	@Test
	public void testMethod3() {
		
		List<Integer> semesterYearList = coursePlan.getSemesterYearList(); 
		
		int expect = 2; 
		
		int actual = semesterYearList.size();
		
		Assert.assertEquals(expect, actual);	
	}
	
	@Test
	public void testMethod4() {		

		List<Subject> subjectList = coursePlan.getSubjectSelectedList(); 
		
		int expect = 1; 
		
		int actual = subjectList.size();
		
		Assert.assertEquals(expect, actual);	
		
	}
	
	/*@Test
	public void testMethod5() {
		 
		boolean expect = false;
		
		boolean actual = coursePlan.setCoursePlanForSave(null);
		
		Assert.assertEquals(expect, actual);	
		
	}*/

	/*
	@Test
	public void testMethod6() {
		
		//CoursePlannerManager test = new CoursePlannerManager();
		
		boolean expect = true;

		boolean actual  = coursePlan.savePlan(); 
		
		Assert.assertEquals(expect, actual);	
		
	}*/
}
