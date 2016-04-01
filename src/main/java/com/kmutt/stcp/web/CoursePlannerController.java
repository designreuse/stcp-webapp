package com.kmutt.stcp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import com.kmutt.stcp.entity.*;
import com.kmutt.stcp.courseplan.*;

@Controller
@RequestMapping("coursePlanner")
@SessionAttributes(value = { "planMng", "courseMng", "courseList" })
public class CoursePlannerController {

	// Field//
	private final Logger logger = LoggerFactory.getLogger(CoursePlannerController.class);

	@Autowired
	private HttpServletRequest request;

	// Action//
	@RequestMapping(value = {"/", "/index" }, method = RequestMethod.GET)
	public String index(HttpSession session, Map<String, Object> model) {

		CourseManager courseMng = this.getCurrentCourseManager(session);
		ArrayList<Course> courseAll = courseMng.getCourseListAll();

		model.put("courselist", courseAll);

		return "coursePlanner/mainPage";

	}

	@RequestMapping(value = { "/courseinfo" }, method = RequestMethod.GET)
	@ResponseBody
	public Course getCourseInfo(HttpSession session, @RequestParam("code") String code) {

		try {

			CourseManager courseMng = this.getCurrentCourseManager(session);
			return courseMng.getCourseByCode(code);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;

		}

	}

	// Method//
	@SuppressWarnings("finally")
	private CourseManager getCurrentCourseManager(HttpSession session) {

		CourseManager _courseManage = null;

		try {

			_courseManage = (CourseManager) session.getAttribute("courseMng");

			if (_courseManage == null) {
				_courseManage = new CourseManager();
			}

		} catch (Exception e) {

			logger.error(e.getMessage());

			_courseManage = new CourseManager();

		} finally {

			session.setAttribute("courseMng", _courseManage);
			return _courseManage;

		}

	}

	@SuppressWarnings("finally")
	private CoursePlanMannager getCurrentPlanManger(HttpSession session) {

		CoursePlanMannager _coursePlanManage = null;

		try {

			_coursePlanManage = (CoursePlanMannager) session.getAttribute("planMng");

			if (_coursePlanManage == null) {
				_coursePlanManage = new CoursePlanMannager();
			}

		} catch (Exception e) {

			logger.error(e.getMessage());

			_coursePlanManage = new CoursePlanMannager();

		} finally {

			session.setAttribute("planMng", _coursePlanManage);
			return _coursePlanManage;

		}

	}

}