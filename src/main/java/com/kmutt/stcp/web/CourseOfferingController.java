package com.kmutt.stcp.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kmutt.stcp.service.CurriculumService;

@Controller
public class CourseOfferingController {
	private final Logger logger = LoggerFactory.getLogger(CourseOfferingController.class);


	//@Autowired CurriculumService curriculumService;

	@RequestMapping(value = "/courseOffering", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
		System.out.println("courseOffering");
		//logger.debug("index() is executed!");

		//model.put("title", "title");
		//model.put("msg", "message");
		
		return "courseOffering/mainPage";
	}
	
}
