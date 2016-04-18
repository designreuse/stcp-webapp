package com.kmutt.stcp.service;

import com.kmutt.stcp.entity.Subject;
import com.kmutt.stcp.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jirapatj on 4/19/16.
 */
@Service("coursePlannerService")
public class CoursePlannerService {

    private final Logger logger = LoggerFactory.getLogger(CoursePlannerService.class);


    @Autowired
    private SubjectRepository subjectRepository;

    public CoursePlannerService() {
    }

    public List<Subject> getSubjectListTest() {
        return subjectRepository.findAll();
    }
}
