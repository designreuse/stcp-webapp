package com.kmutt.stcp.manager;

import com.kmutt.stcp.entity.CoursePlan;
import com.kmutt.stcp.entity.Prerequisite;
import com.kmutt.stcp.entity.Subject;
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
   
}
