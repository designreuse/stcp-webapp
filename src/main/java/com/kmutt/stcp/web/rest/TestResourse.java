package com.kmutt.stcp.web.rest;

import com.kmutt.stcp.entity.Course;
import com.kmutt.stcp.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jirapatj on 3/20/16.
 */

@Controller
@RequestMapping("/test/rest")
public class TestResourse {

	@Autowired
	private CourseRepository courseRepository;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Course> getDummyEmployee() {

		Course course = new Course();
//		course.setName("name");
//		course.setCode("code");

		ArrayList<Course> courseList = new ArrayList<>();
		courseList.add(course);
		courseList.add(course);
		courseList.add(course);
		
		return courseList;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody boolean createEmployee(@RequestBody Course course) {

		Course c = new Course();
//		c.setCode(course.getCode());
//		c.setName(course.getName());

		boolean _success = false;
		try {
			System.out.println("create course");
			_success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _success;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	public @ResponseBody Course deleteEmployee(@PathVariable("code") String code) {

		Course c = new Course();
//		c.setCode(code);

		courseRepository.delete(c);
		return c;
	}

}
