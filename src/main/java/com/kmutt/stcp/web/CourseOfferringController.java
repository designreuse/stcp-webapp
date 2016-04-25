package com.kmutt.stcp.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kmutt.stcp.entity.Curriculum;
import com.kmutt.stcp.entity.CurriculumSubject;
import com.kmutt.stcp.entity.Prerequisite;
import com.kmutt.stcp.entity.Subject;
import com.kmutt.stcp.manager.CourseManager;
import com.kmutt.stcp.manager.SubjectManager;
import com.kmutt.stcp.repository.CurriculumRepository;
import com.kmutt.stcp.repository.CurriculumSubjectRepository;
import com.kmutt.stcp.repository.SubjectRepository;

@Controller
@RequestMapping("/courseofferring")
public class CourseOfferringController {
	 private final Logger logger = LoggerFactory.getLogger(TestController.class);
	 
	 	@Autowired
	    private SubjectManager subjectManager;
	 	
	 	@Autowired
	    private SubjectRepository subjectRepository;
	 	
	 	@Autowired
	    private CurriculumSubjectRepository curriculumSubjectRepository;
	 	
	 	@Autowired
	    private CurriculumRepository curriculumRepository;

	    @RequestMapping(value = "/courseofferring", method = RequestMethod.GET)
	    public String courseofferring(Map<String, Object> model) {
	        return "courseOfferring/courseofferring";
	    }
	    
	    @RequestMapping(value = "/managesubject", method = RequestMethod.GET)
	    public String managesubject(Map<String, Object> model) {
	        return "courseOfferring/managesubject";
	    }
	    
	    @RequestMapping(value = "/managesubject", method = RequestMethod.POST)
	    public String searchsubject(HttpServletRequest  request,HttpServletResponse response) {
	    	List<Subject> subjObj = subjectManager.getAllSubject();
	    	
	    	request.setAttribute("subjectSearchList", subjObj);
	    	
	        return "courseOfferring/managesubject";
	    }
	    
	    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
	    public String prepareAddSubject(Model model) {
	    	model.addAttribute("subjectForm", new Subject());
	        return "courseOfferring/addSubject";
	    }
	    
	    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
	    public String addSubject(@ModelAttribute("subjectForm") Subject subject,@ModelAttribute("preSubjectId") String preSubjectId) {
	    	subjectManager.addSubject(subject,preSubjectId);
	        return "courseOfferring/managesubject";
	    }
	    
	    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	    public String addCourse(Model model) {
	    	model.addAttribute("curriculumList",curriculumRepository.findAll());
	    	model.addAttribute("subjectList",subjectRepository.findAll());
	        return "courseOfferring/addCourse";
	    }
	    
	    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	    public String addCourse(HttpServletRequest  request,HttpServletResponse response) {
	    	String cur = request.getParameter("curriculum");
	    	String []sub = request.getParameterValues("subject");
	    
	    	List<Curriculum> curriculumList = curriculumRepository.queryHQL("from Curriculum where id = "+cur);
	    	if(curriculumList != null && curriculumList.size() > 0){
		    	for(int i = 0;i<sub.length;i++)
		    	{
		    		List<Subject> subjectList = subjectRepository.queryHQL("from Subject where id = "+sub[i]);
		    		if(subjectList != null && subjectList.size() > 0){
			    		CurriculumSubject curriculumSubject = new CurriculumSubject();
			    		curriculumSubject.setCurriculum(curriculumList.get(0));
			    		curriculumSubject.setSubject(subjectList.get(0));
			    		
			    		curriculumSubjectRepository.create(curriculumSubject); // insert 
		    		}
		    	}
		    	
	    	}
	    	
	        return "redirect:/courseofferring/addCourse";
	    }
	    
	    @RequestMapping(value = "/managecurriculum", method = RequestMethod.GET)
	    public String managecurriculum(Map<String, Object> model) {
	        return "courseOfferring/managecurriculum";
	    }
	    
	    @RequestMapping(value = "/managecourse", method = RequestMethod.GET)
	    public String managecourse(Map<String, Object> model) {
	        return "courseOfferring/managecourse";
	    }
	
	    @RequestMapping(value = "/DetailCurriculum", method = RequestMethod.GET)
	    public String DetailCurriculum(Map<String, Object> model) {
	        return "courseOfferring/DetailCurriculum";
	    }
	    
	    @ModelAttribute("subjectTypeList")
		public Map<String,String> subjectTypeList() {
			
			Map<String,String> subjectTypeList = new LinkedHashMap<String,String>();
			subjectTypeList.put("1", "วิชาเลือกทั่วไป");
			subjectTypeList.put("2", "วิชาเฉพาะ");
			subjectTypeList.put("3", "วิชาเลือกเสรี");
			
			return subjectTypeList;
		}
	    
	    @ModelAttribute("curriculumList")
		public Map<String,String> curriculumList() {
			Map<String,String> curriculumList = new LinkedHashMap<String,String>();
			
			/*curriculumList.put("1", "วิชาเลือกทั่วไป");
			curriculumList.put("2", "วิชาเฉพาะ");
			curriculumList.put("3", "วิชาเลือกเสรี");*/
			return curriculumList;
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
