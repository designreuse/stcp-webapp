package com.kmutt.stcp.manager;

import com.kmutt.stcp.entity.CoursePlan;
import com.kmutt.stcp.entity.CurriculumSubject;
import com.kmutt.stcp.entity.Prerequisite;
import com.kmutt.stcp.entity.Subject;
import com.kmutt.stcp.repository.CurriculumSubjectRepository;
import com.kmutt.stcp.repository.PrerequisiteRepository;
import com.kmutt.stcp.repository.SubjectRepository;

import java.util.ArrayList;
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
		
//		Prerequisite prerequisite = new Prerequisite();
//		
//		Subject preSubject = new Subject();
//		preSubject.setId(Integer.parseInt(preSubjectId));
//		
//		prerequisite.setSubjectByPresubjectId(preSubject);
//		prerequisite.setSubjectBySubjectId(subject);
//		
//		Set<Prerequisite> prerequisitesForSubjectId = new HashSet<Prerequisite>();
//		prerequisitesForSubjectId.add(prerequisite);
//		
//		subject.setPrerequisitesForPresubjectId(prerequisitesForSubjectId);
//		subject.setPrerequisitesForSubjectId(prerequisitesForSubjectId);

		subjectRepository.create(subject);
		
		
		
	}
	
	// Method//
		public void addPrerequisite(Subject subject,String preSubjectId) {
			
			Subject mainSubject = new Subject();
			String hqlMainSubject = "from Subject where subjectCode = '"+subject.getSubjectCode()+"' "
					+ "									and nameThai = '"+subject.getNameThai()+"' "
					+ "									and nameEng = '"+subject.getNameEng()+"' "
					+ "									and subjectType = '"+subject.getSubjectType()+"' "
					+ "									and credit = '"+subject.getCredit()+"' "
					+ "									and detailThai = '"+subject.getDetailThai()+"' "
					+ "									and detailEng = '"+subject.getDetailEng()+"' ";
			
			mainSubject = (Subject) subjectRepository.queryHQL(hqlMainSubject).get(0);
			
			
			Subject foreignSubject = new Subject();
			
			String hqlForeignSubject = "from Subject where id = "+Integer.parseInt(preSubjectId)+" ";
			
			foreignSubject = (Subject) subjectRepository.queryHQL(hqlForeignSubject).get(0);
			
			Prerequisite prerequisite = new Prerequisite();
			prerequisite.setSubjectByPresubjectId(foreignSubject);
			prerequisite.setSubjectBySubjectId(mainSubject);
			
			prerequisiteRepository.create(prerequisite);
		}
	
		
	// Method//
	public void updateSubject(Subject subject) {
		subjectRepository.update(subject);
		
		String hql = "from Prerequisite where subjectBySubjectId.id = "+subject.getId()+" ";
			
		List<Prerequisite> prerequisiteList = (List<Prerequisite>) prerequisiteRepository.queryHQL(hql);
			
		if(prerequisiteList.size()!=0){
			for(int i=0; i<prerequisiteList.size(); i++){
				prerequisiteRepository.delete(prerequisiteList.get(i));
			}
		}
		
	}
	
	// Method//
	public void deleteSubject(int subjectId) {
//		
//		List<CurriculumSubject> curriculumSubject = new ArrayList<CurriculumSubject>();
//		String hqlCurSub = "from CurriculumSubject where subject.id = "+subjectId+" ";
//		curriculumSubject = curriculumSubjectRepository.queryHQL(hqlCurSub);
//		
//		List<Prerequisite> preSub = new ArrayList<Prerequisite>();
//		String hqlPreSub = "from Prerequisite where subjectBySubjectId.id = "+subjectId+" ";
//		preSub = prerequisiteRepository.queryHQL(hqlPreSub);
//		
//		List<Prerequisite> preRequi = new ArrayList<Prerequisite>();
//		String hqlPreRequi = "from Prerequisite where subjectByPresubjectId.id = "+subjectId+" ";
//		preRequi = prerequisiteRepository.queryHQL(hqlPreRequi);
//		
//		for(int i=0; i<curriculumSubject.size(); i++){
//			curriculumSubjectRepository.delete(curriculumSubject.get(i));
//		}
//		
//		for(int j=0; j<preSub.size(); j++){
//			prerequisiteRepository.delete(preSub.get(j));
//		}
//		
//		for(int j=0; j<preRequi.size(); j++){
//			prerequisiteRepository.delete(preRequi.get(j));
//		}
		
		Subject subject = new Subject();
		
		String hql = "from Subject where id = "+subjectId+" ";
		
		subject = (Subject) subjectRepository.queryHQL(hql).get(0);
		
		subjectRepository.delete(subject);
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
					hql += " and sub.subjectType = '"+subjectType+"'";
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
					hql += " and sub.subjectType = '"+subjectType+"'";
				}
				
				if(!status.equals("")){
					hql += " and status = "+status;
				}
				
				if(!subjectCode.equals("")){
					subjectCode = subjectCode.replace(" ", "");
					hql += " and REPLACE(subject_code,' ','') like '%"+subjectCode+"%' ";
				}
			}
			
			List<CurriculumSubject> curriculumSubjectRepositoryList = curriculumSubjectRepository.queryHQL(hql);
			//System.out.println(curriculumSubjectRepositoryList.size()+"--");
			
			return curriculumSubjectRepositoryList;
		}
   
		
	// Method//
	public Subject getSubjectById(int id) {
		
		Subject subject = new Subject();
		
		String hql = "from Subject where id = "+id+" ";
		
		subject = (Subject) subjectRepository.queryHQL(hql).get(0);
		
		return subject;
	}
	
	// Method//
		public List<Subject> getSubjectByCriteria(String subjectType,String status,String subjectCode) {
			
			
			
			String hql = "from Subject s where 1=1 ";
			
			if(!subjectType.equals("")){
				hql += " and s.subjectType = '"+subjectType+"'";
			}
			
			if(!status.equals("")){
				hql += " and s.status = "+status;
			}
			
			if(!subjectCode.equals("")){
				subjectCode = subjectCode.replace(" ", "");
				hql += " and REPLACE(subject_code,' ','') like '%"+subjectCode+"%' ";
			}
			
			List<Subject> subject = subjectRepository.queryHQL(hql);
			
			return subject;
		}
	
	public Object getPrerequisiteById(int id){
		
		String hql = "from Prerequisite pre join pre.subjectBySubjectId as subs "
				+ "							join pre.subjectByPresubjectId as subp "
				+ "							 where subs.id = "+id+" ";
		
		List<Prerequisite> prerequisiteList = prerequisiteRepository.queryHQL(hql);
		
		if(prerequisiteList.size()!=0){
			return prerequisiteList;
		}else{
			return null;
		}
		
		
	}
	
}
