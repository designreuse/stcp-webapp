package com.kmutt.stcp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
=======
import com.kmutt.stcp.entity.*;
import com.kmutt.stcp.entity.courseplan.MessageResult;
import com.kmutt.stcp.entity.courseplan.PlanMessageRequest;
import com.kmutt.stcp.courseplan.*;
>>>>>>> 98a9c70d50c23ea4337a0e10c076624fa2b77eaa

@Controller
@RequestMapping("coursePlanner")
@SessionAttributes(value = { "planMng", "courseMng", "courseList" })
public class CoursePlannerController {

	// Field//
	private final Logger logger = LoggerFactory.getLogger(CoursePlannerController.class);

	@Autowired
	private HttpServletRequest request;

	// Action//
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(HttpSession session, Map<String, Object> model) {

		CourseManager courseMng = this.getCurrentCourseManager(session);
		CoursePlanMannager planMng = this.getCurrentPlanManger(session);

		List<Integer> semesterYearList = planMng.getSemesterYearList();
		model.put("semesterYearList", semesterYearList);

		List<CoursePlan> semesterPlan = planMng.getCoursePlanList();
		model.put("semesterList", semesterPlan);

		List<Subject> subjectAll = courseMng.getSubjectList();
		subjectAll = bindSubjectIsSelected(subjectAll, planMng.getSubjectSelectedList());
		model.put("subjectlist", subjectAll);

		return "coursePlanner/mainPage";

	}

	@RequestMapping(value = { "/courseplan/{semesteryear}" }, method = RequestMethod.GET)
	@ResponseBody
	public List<CoursePlan> getCoursePlanBySemester(HttpSession session, @PathVariable("semesteryear") String semesterYear) {

		try {

			int _semesterYear = 0;

			try {

				_semesterYear = Integer.parseInt(semesterYear);

			} catch (NumberFormatException e) {

				System.out.println(e.getMessage());
				return null;

			}

			CoursePlanMannager coursePlanMng = this.getCurrentPlanManger(session);

			return coursePlanMng.getCoursePlanList(_semesterYear);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			return null;

		}

	}

	@RequestMapping(value = { "/searchSubject" }, method = RequestMethod.GET)
	@ResponseBody
	public List<Subject> searchSubject(HttpSession session, @RequestParam("textsearch") String textSearch) {

		List<Subject> subjectSearched = new ArrayList<>();

		try {

			CourseManager courseMng = this.getCurrentCourseManager(session);
			subjectSearched = courseMng.searchSubject(textSearch);

		} catch (Exception e) {

			logger.error("Method:searchSubject|Err:" + e.getMessage());
			subjectSearched = new ArrayList<>();
		}

		return subjectSearched;

	}

	@RequestMapping(value = { "/saveplan" }, method = RequestMethod.POST)
	@ResponseBody
	public MessageResult savePlan(HttpSession session, @RequestBody List<PlanMessageRequest> messageRequest) {

		MessageResult result = new MessageResult();

		try {

			if (messageRequest != null) {

				CoursePlanMannager plnManger = this.getCurrentPlanManger(session);

				if (plnManger.setCoursePlanForSave(messageRequest)) {

					if(plnManger.savePlan()) {

						result.StatusCode = "000";
						result.IsError = false;
						result.ErrorDescription = "";
						
					} else {
						
						result.StatusCode = "103";
						result.IsError = true;
						result.ErrorDescription = "can't save plan in database.";
						
					}

				} else {

					result.StatusCode = "102";
					result.IsError = true;
					result.ErrorDescription = "message can't be parsed.";

				}
				
			} else {

				result.StatusCode = "101";
				result.IsError = true;
				result.ErrorDescription = "message are empty";
				
			}

		} catch (Exception e) {

			logger.error("Method:savePlan|Err:" + e.getMessage());

			result.StatusCode = "100";
			result.IsError = true;
			result.ErrorDescription = "can't save plan.";

		}

		return result;

	}

	// Method//
	@SuppressWarnings("finally")
	private CourseManager getCurrentCourseManager(HttpSession session) {

		CourseManager _courseManage = null;

		try {

			_courseManage = (CourseManager) session.getAttribute("courseMng");

			if (_courseManage == null) {
				_courseManage = new CourseManager(new Account());
			}

		} catch (Exception e) {

			logger.error(e.getMessage());

			_courseManage = new CourseManager(new Account());

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
				_coursePlanManage = new CoursePlanMannager(new Account());
			}

		} catch (Exception e) {

			logger.error(e.getMessage());

			_coursePlanManage = new CoursePlanMannager(new Account());

		} finally {

			session.setAttribute("planMng", _coursePlanManage);
			return _coursePlanManage;

		}

	}

	private List<Subject> bindSubjectIsSelected(List<Subject> subjectAll, List<Subject> subjectSelectedList) {

		for (Subject subject : subjectAll) {

			for (Subject subjectSelected : subjectSelectedList) {

				if (subject.getSubjectCode().toLowerCase().equals(subjectSelected.getSubjectCode().toLowerCase())
						|| subject.getId() == subjectSelected.getId()) {
					subject.setStatus(1);
				}
			}
		}

		return subjectAll;

	}
}
