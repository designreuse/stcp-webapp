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
@RequestMapping("coursePlanner")
@SessionAttributes(value = { "planMng", "courseMng", "courseList" })
public class CoursePlannerController {

	// Field//
	private final Logger logger = LoggerFactory.getLogger(CoursePlannerController.class);

    @Autowired
    private CourseManager courseManager;

    @Autowired
    private CoursePlannerManager coursePlannerManager;

//    @Autowired
//	private HttpServletRequest request;

	// Action//
	@RequestMapping(value = { "/test", "/index" }, method = RequestMethod.GET)
	public String test(HttpSession session, Map<String, Object> model) {


        List<Subject> list = courseManager.testCourseManager();


		return "coursePlanner/mainPage";

	}

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(HttpSession session, Map<String, Object> model) {

		//TODO: Dummy Student Account
		Account student = new Account();
		student.setId(1);
		session.setAttribute("account", student);
		
		CourseManager courseMng = this.getCurrentCourseManager(session);
		CoursePlannerManager planMng = this.getCurrentPlanManger(session);

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

			CoursePlannerManager coursePlanMng = this.getCurrentPlanManger(session);

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

				CoursePlannerManager plnManger = this.getCurrentPlanManger(session);

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
				//TODO: change session name to get account
				_courseManage = new CourseManager((Account) session.getAttribute("account"));
			}

		} catch (Exception e) {

			logger.error(e.getMessage());

			_courseManage = new CourseManager(null);

		} finally {

			session.setAttribute("courseMng", _courseManage);
			return _courseManage;

		}

	}

	@SuppressWarnings("finally")
	private CoursePlannerManager getCurrentPlanManger(HttpSession session) {

		CoursePlannerManager _coursePlanManage = null;

		try {

			_coursePlanManage = (CoursePlannerManager) session.getAttribute("planMng");

			if (_coursePlanManage == null) {
				//TODO: change session name to get account
				_coursePlanManage = new CoursePlannerManager((Account) session.getAttribute("account"));
			}

		} catch (Exception e) {

			logger.error(e.getMessage());

			_coursePlanManage = new CoursePlannerManager(null);

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
