package com.kmutt.stcp.web;

import com.kmutt.stcp.manager.CourseManager;
import com.kmutt.stcp.manager.CoursePlannerManager;
import com.kmutt.stcp.entity.Account;
import com.kmutt.stcp.entity.CoursePlan;
import com.kmutt.stcp.entity.Subject;
import com.kmutt.stcp.dto.MessageResult;
import com.kmutt.stcp.dto.PlanMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;

//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SecurityController {
	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody 
	public List<Subject> Login(HttpSession session, @RequestParam("textsearch") String textSearch) {

		List<Subject> subjectSearched = new ArrayList<>();

		try {

			//CourseManager courseMng = this.getCurrentCourseManager(session);
			//subjectSearched = courseMng.searchSubject(textSearch);

		} catch (Exception e) {

			logger.error("Method:searchSubject|Err:" + e.getMessage());
			subjectSearched = new ArrayList<>();
		}

		return subjectSearched;
	}
	
}
