package com.kmutt.stcp.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kmutt.stcp.entity.Curriculum;
import com.kmutt.stcp.entity.CurriculumSubject;
import com.kmutt.stcp.entity.Subject;
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
	    public String searchsubject(HttpServletRequest  request,HttpServletResponse response,
	    							@ModelAttribute("curiID") String curiID,
	    							@ModelAttribute("subjectType") String subjectType,
	    							@ModelAttribute("status") String status,
	    							@ModelAttribute("subjectCode") String subjectCode) {
	    	
	    	if(curiID.equals("") && subjectType.equals("") && status.equals("") && subjectCode.equals("")){
	    		List<Subject> subjObj = subjectManager.getAllSubject();
		    	
	    		request.setAttribute("entity", "subject");
		    	request.setAttribute("subjectSearchList", subjObj);
	    	}else{
	    		if(curiID.equals("")){
	    			List<Subject> subjObj = subjectManager.getSubjectByCriteria(subjectType, status, subjectCode);
	    			
	    			request.setAttribute("entity", "subject");
			    	request.setAttribute("subjectSearchList", subjObj);
	    		}else{
	    			List<CurriculumSubject> curSubObj = subjectManager.searchProjectByCriteria(curiID, subjectType, status, subjectCode);
		    		
		    		request.setAttribute("entity", "crriculumSubject");
		    		request.setAttribute("subjectSearchList", curSubObj);
	    		}
	    		
	    	}
	    	
	        return "courseOfferring/managesubject";
	    }
	    
	    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
	    public String prepareAddSubject(Model model) {
	    	model.addAttribute("subjectForm", new Subject());
	        return "courseOfferring/addSubject";
	    }
	    
	    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
	    public String addSubject(Model model,@ModelAttribute("subjectForm") Subject subject,@ModelAttribute("preSubjectId") String preSubjectId) {
	    	subject.setStatus(1);
	    	subjectManager.addSubject(subject,preSubjectId);
	    	
	    	if(preSubjectId!=null){
	    		if(!preSubjectId.equals("")){
	    			subjectManager.addPrerequisite(subject, preSubjectId);
		    	}
	    	}
	    	
	    	model.addAttribute("addSuccess", "Y");
	    	
	        return "courseOfferring/managesubject";
	    }
	    
	    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	    public String addCourse(Model model) {
	    	model.addAttribute("OatCurriculumList",curriculumRepository.findAll());
	    	model.addAttribute("subjectList",subjectRepository.findAll());
	    	model.addAttribute("OatCurriculumSubjectList",curriculumSubjectRepository.findAll());
	    	
	        return "courseOfferring/addCourse";
	    }
	    
	    @RequestMapping(value = "/editSubject", method = RequestMethod.GET)
	    public String prepareEditSubject(Model model,@RequestParam("subjectId") String id) {
	    	
	    	if(id!=null && !id.equals("")){
	    		Subject subject = new Subject();
	    		subject = subjectManager.getSubjectById(Integer.parseInt(id));
	    		

	    		model.addAttribute("prerequisite",subjectManager.getPrerequisiteById(Integer.parseInt(id)));
	    		
		    	model.addAttribute("subjectForm", subject);
		        return "courseOfferring/editSubject";
	    	}else{
	    		 return "courseOfferring/managesubject";
	    	}
	    	
	    }
	    
	    @RequestMapping(value = "/editSubject", method = RequestMethod.POST)
	    public String editSubject(Model model,@ModelAttribute("subjectForm") Subject subject,@ModelAttribute("preSubjectId") String preSubjectId,@ModelAttribute("preRequisiteId") String preRequisiteId) {
	    	
	    	preSubjectId = (preSubjectId.equals("")||preSubjectId==null)?"0":preSubjectId;
	    	preRequisiteId = (preRequisiteId.equals("")||preRequisiteId==null)?"0":preRequisiteId;
	    	
	    	subjectManager.updateSubject(subject, Integer.parseInt(preRequisiteId), Integer.parseInt(preSubjectId));
	    	
	    	model.addAttribute("editSuccess", "Y");
	        return "courseOfferring/managesubject";
	    }
	    
	    
	    @RequestMapping(value = "/deleteSubject", method = RequestMethod.POST)
	    public String deleteSubject(Model model,@RequestParam("subjectId") String id) {
	    	
	    	if(id!=null && !id.equals("")){
	    		subjectManager.deleteSubject(Integer.parseInt(id));
	    	}
	    	
	        return "redirect:managesubject";
	    }
	    
	    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	    public String addCourse(HttpServletRequest  request,HttpServletResponse response) {
	    	String cur = request.getParameter("curriculum");
	    	String []sub = request.getParameterValues("subject");
	    
	    	Curriculum curriculum = curriculumRepository.findOne(Integer.parseInt(cur));
	    	for(int i = 0;i<sub.length;i++)
	    	{
	    		Subject subject = subjectRepository.findOne(Integer.parseInt(sub[i]));
	    		CurriculumSubject curriculumSubject = new CurriculumSubject();
	    		curriculumSubject.setCurriculum(curriculum);
	    		curriculumSubject.setSubject(subject);
	    		
	    		curriculumSubjectRepository.create(curriculumSubject); // insert 
	    	}
	    	
	        return "redirect:/courseofferring/managecourse";
	    }
	    
	    @RequestMapping(value = "/editCourse", method = RequestMethod.POST)
	    public String editCourse(HttpServletRequest  request,HttpServletResponse response) {
	    	String cur = request.getParameter("curriculum");
	    	String []sub = request.getParameterValues("subject");
	    	
	    	String hql = " from CurriculumSubject as curs join curs.curriculum as cur "
	    			+ "								join curs.subject as sub  "
	    			+ "	  where  cur.id ="+Integer.parseInt(cur);
	    	
	    	List<Object[]> curriculumSub = curriculumSubjectRepository.queryHQLByField(hql);
	    	
	    	for(int j=0; j<curriculumSub.size(); j++){
	    		CurriculumSubject curriculumSubjectObj = new CurriculumSubject();
	    		
	    				
	    		Object[] objTemp =	curriculumSub.get(j);
	    		
	    		curriculumSubjectRepository.delete((CurriculumSubject) objTemp[0]);
	    	}
	    	
	    	Curriculum curriculum = curriculumRepository.findOne(Integer.parseInt(cur));
	    	for(int i = 0;i<sub.length;i++)
	    	{
	    		Subject subject = subjectRepository.findOne(Integer.parseInt(sub[i]));
	    		CurriculumSubject curriculumSubject = new CurriculumSubject();
	    		curriculumSubject.setCurriculum(curriculum);
	    		curriculumSubject.setSubject(subject);
	    		
	    		curriculumSubjectRepository.create(curriculumSubject); // insert 
	    	}
	    	
	        return "redirect:/courseofferring/managecourse";
	    }
	    
	    
	    @RequestMapping(value = "/managecourse", method = RequestMethod.POST)
	    public String manageCourse(HttpServletRequest  request,HttpServletResponse response) {
	    	
	    	String cur = request.getParameter("curriculum");
	    	String year = request.getParameter("year");
	    	
	    	String sql = "select * from stcpdb.curriculum_subject cs "
	    			+ "inner join stcpdb.curriculum cur on cs.curriculum_id = cur.id "
	    			+ "inner join stcpdb.subject sub on cs.subject_id = sub.id where 1 = 1 " ;
	    	
	    	if(!"".equals(year))
	    	{
	    		sql += "and cur.start_year= '"+year+ "'";
	    	}
	    	
	    	if(!"".equals(cur))
	    	{
	    		sql += "and cur.id = '"+cur +"' " ;
	    	}
	    
	    	List <CurriculumSubject> curSub = curriculumSubjectRepository.querySQL(sql);
	    	if(curSub != null)
	    	{
	    		//request.setAttribute("courseSearchList", curSub);
	    		HashMap<Curriculum,List<Subject>> hashmap=new HashMap<Curriculum,List<Subject>>();
	    		
	    		for (CurriculumSubject cs : curSub) {
	    			List<Subject> listSub = hashmap.get(cs.getCurriculum());
	    			
	    			if(listSub == null)
	    			{
	    				listSub = new ArrayList();
	    			}
	    			listSub.add(cs.getSubject());
	    			hashmap.put(cs.getCurriculum(),listSub);
	    		}
	    		request.setAttribute("courseSearchList", hashmap);
	    		
	    	}
	    	
	    	request.setAttribute("curriculumYear",year);
    		request.setAttribute("curriculumName", cur);
    		
    		List<Curriculum> curList = curriculumRepository.findAll();
    		if(curList != null)
    		{
    			System.out.println("Size >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+curList.size());
    			request.setAttribute("OatCurriculumList",curList);
    			
    		}
    		
	        return "courseOfferring/managecourse";
	    }
	    
	    @RequestMapping(value = "/editCourse", method = RequestMethod.GET)
	    public String prepareEditCourse(Model model,HttpServletRequest  request) {
	    	
	    	model.addAttribute("OatCurriculumList",curriculumRepository.findAll());
	    	model.addAttribute("subjectList",subjectRepository.findAll());
	    	
	    	String curId = request.getParameter("courseId");
	    	
	    	String hql = " from CurriculumSubject as curs join curs.curriculum as cur "
	    			+ "								join curs.subject as sub  "
	    			+ "	  where  cur.id ="+Integer.parseInt(curId);
	    	List<CurriculumSubject> curriculumSub = curriculumSubjectRepository.queryHQL(hql);
	    	
	    	model.addAttribute("curriculumSub",curriculumSub);
	    	
	        return "courseOfferring/editCourse";
	    }
	    
	    
	    @RequestMapping(value = "/managecurriculum", method = RequestMethod.GET)
	    public String managecurriculum(Map<String, Object> model) {
	        return "courseOfferring/managecurriculum";
	    }
	    
	    @RequestMapping(value = "/managecourse", method = RequestMethod.GET)
	    public String managecourse(Model model) {
	    	model.addAttribute("OatCurriculumList",curriculumRepository.findAll());
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
