package com.kmutt.stcp.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kmutt.stcp.entity.Prerequisite;
import com.kmutt.stcp.entity.Subject;
import com.kmutt.stcp.manager.CourseManager;
import com.kmutt.stcp.manager.SubjectManager;

@Controller
@RequestMapping("/courseofferring")
public class CourseOfferringController {
	 private final Logger logger = LoggerFactory.getLogger(TestController.class);
	 
	 	@Autowired
	    private SubjectManager subjectManager;

	    @RequestMapping(value = "/courseofferring", method = RequestMethod.GET)
	    public String courseofferring(Map<String, Object> model) {
	        return "courseofferring";
	    }
	    
	    @RequestMapping(value = "/managesubject", method = RequestMethod.GET)
	    public String managesubject(Map<String, Object> model) {
	        return "managesubject";
	    }
	    
	    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
	    public String prepareAddSubject(Model model) {
	    	model.addAttribute("subjectForm", new Subject());
	        return "addSubject";
	    }
	    
	    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
	    public String addSubject(@ModelAttribute("subjectForm") Subject subject,@ModelAttribute("preSubjectId") String preSubjectId) {
	    	subjectManager.addSubject(subject,preSubjectId);
	        return "managesubject";
	    }
	    
	    @RequestMapping(value = "/managecurriculum", method = RequestMethod.GET)
	    public String managecurriculum(Map<String, Object> model) {
	        return "managecurriculum";
	    }
	    
	    @RequestMapping(value = "/managecourse", method = RequestMethod.GET)
	    public String managecourse(Map<String, Object> model) {
	        return "managecourse";
	    }
	
	    @RequestMapping(value = "/DetailCurriculum", method = RequestMethod.GET)
	    public String DetailCurriculum(Map<String, Object> model) {
	        return "DetailCurriculum";
	    }
	    
	    @ModelAttribute("subjectTypeList")
		public Map<String,String> subjectTypeList() {
			
			Map<String,String> subjectTypeList = new LinkedHashMap<String,String>();
			subjectTypeList.put("1", "วิชาเลือกทั่วไป");
			subjectTypeList.put("2", "วิชาเฉพาะ");
			subjectTypeList.put("3", "วิชาเลือกเสรี");
			
			return subjectTypeList;
		}
	    
	    
	    @ModelAttribute("creditList")
		public Map<String,String> creditList() {
			
			Map<String,String> creditList = new LinkedHashMap<String,String>();
			creditList.put("1", "1");
			creditList.put("2", "2");
			creditList.put("3", "3");
			
			return creditList;
		}
	    
	    
	    @ModelAttribute("subjectList")
		public Map<String,String> subjectList() {
			
			Map<String,String> subjectList = new LinkedHashMap<String,String>();
			
			List<Subject> subjObj = subjectManager.getAllSubject();
			
			for(int i=0; i<subjObj.size(); i++){
				subjectList.put(subjObj.get(i).getId()+"", subjObj.get(i).getSubjectCode()+"-"+subjObj.get(i).getNameEng());
			}
			
			return subjectList;
		}
	    
	    
}
