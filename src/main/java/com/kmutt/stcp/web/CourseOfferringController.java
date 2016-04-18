package com.kmutt.stcp.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/courseofferring")
public class CourseOfferringController {
	 private final Logger logger = LoggerFactory.getLogger(TestController.class);

	    @RequestMapping(value = "/courseofferring", method = RequestMethod.GET)
	    public String courseofferring(Map<String, Object> model) {
	        return "courseofferring";
	    }
	    
	    @RequestMapping(value = "/managesubject", method = RequestMethod.GET)
	    public String managesubject(Map<String, Object> model) {
	        return "managesubject";
	    }
	    
	    @RequestMapping(value = "/managecurriculum", method = RequestMethod.GET)
	    public String managecurriculum(Map<String, Object> model) {
	        return "managecurriculum";
	    }
	    
	    @RequestMapping(value = "/mergesubjectandcurriculum", method = RequestMethod.GET)
	    public String mergesubjectandcurriculum(Map<String, Object> model) {
	        return "mergesubjectandcurriculum";
	    }
	
	    @RequestMapping(value = "/DetailCurriculum", method = RequestMethod.GET)
	    public String DetailCurriculum(Map<String, Object> model) {
	        return "DetailCurriculum";
	    }
}
