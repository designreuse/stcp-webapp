package com.kmutt.stcp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class CoursePlannerController {
	private final Logger logger = LoggerFactory.getLogger(CoursePlannerController.class);


	//@Autowired CurriculumService curriculumService;

	@RequestMapping(value = "/coursePlanner", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		//logger.debug("index() is executed!");

		//model.put("title", "title");
		//model.put("msg", "message");
		
		return "coursePlanner/mainPage";
	}
	
}
