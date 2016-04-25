package com.kmutt.stcp.manager;

import com.kmutt.stcp.entity.CoursePlan;
import com.kmutt.stcp.entity.CurriculumSubject;
import com.kmutt.stcp.entity.Prerequisite;
import com.kmutt.stcp.entity.Subject;
import com.kmutt.stcp.repository.CurriculumSubjectRepository;
import com.kmutt.stcp.repository.PrerequisiteRepository;
import com.kmutt.stcp.repository.SubjectRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("subjectManager")
public class SubjectManager {

    // Field//
    private final Logger logger = LoggerFactory.getLogger(SubjectManager.class);

    //@Autowired
	//private SubjectService subjectService;
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private CurriculumSubjectRepository curriculumSubjectRepository;

    
    @Autowired
    private PrerequisiteRepository prerequisiteRepository;

    // Constructor//
    public SubjectManager() { }


    // Method//
	public void addSubject(Subject subject,String preSubjectId) {
		
		subjectRepository.create(subject);
		
//		System.out.println(subject.getId()+"---------------");
//
//		Prerequisite prerequisite = new Prerequisite();
//		
//		Subject preSubject = new Subject();
//		preSubject.setId(Integer.parseInt(preSubjectId));
//		
//		prerequisite.setSubjectByPresubjectId(preSubject);
//		prerequisite.setSubjectBySubjectId(subject);
//		
//		prerequisiteRepository.create(prerequisite);
	}
	
	// Method//
	public void updateSubject(Subject subject) {
		subjectRepository.update(subject);
	}
	
	// Method//
	public void deleteSubject(int subjectId) {
		subjectRepository.deleteById(subjectId);
	}

	// Method//
		public List<Subject> getAllSubject() {
			return subjectRepository.findAll();
		}
	
		// Method//
		public List<CurriculumSubject> searchProjectByCriteria(String curiID,String subjectType,String status,String subjectCode) {
			
			String hql = "";
			
			if(!curiID.equals("")){
				hql = "from CurriculumSubject as curs join curs.curriculum as cur"
						+ "							  join curs.subject as sub  "
						+ " where cur.id="+curiID;
				
				if(!subjectType.equals("")){
					hql += " and sub.subjectType = "+subjectType;
				}
				
				if(!status.equals("")){
					hql += " and sub.status = "+status;
				}
				
				if(!subjectCode.equals("")){
					hql += " and sub.subjectCode = '"+subjectCode+"' ";
				}
			}else{
				hql = "from Subject where 1=1 ";
				
				if(!subjectType.equals("")){
					hql += " and subjectType = "+subjectType;
				}
				
				if(!status.equals("")){
					hql += " and status = "+status;
				}
				
				if(!subjectCode.equals("")){
					hql += " and subjectCode = '"+subjectCode+"' ";
				}
			}
			
			List<CurriculumSubject> curriculumSubjectRepositoryList = curriculumSubjectRepository.queryHQL(hql);
			//System.out.println(curriculumSubjectRepositoryList.size()+"--");
			
			return curriculumSubjectRepositoryList;
		}
   
}
