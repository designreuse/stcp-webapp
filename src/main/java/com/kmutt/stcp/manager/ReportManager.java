package com.kmutt.stcp.manager;

import com.kmutt.stcp.entity.Curriculum;
import com.kmutt.stcp.repository.AccountRepository;
import com.kmutt.stcp.repository.CourseRepository;
import com.kmutt.stcp.repository.CurriculumRepository;
import com.kmutt.stcp.repository.common.AbstractHibernateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Gift on 25-Apr-16.
 */
@Component("reportManager")
public class ReportManager {
    @Autowired
    private CurriculumRepository curriculumRepository;

    public Integer findCourseId(String name,String startedYear) {
        String hql = "SELECT c FROM Curriculum c"
                        + " WHERE name LIKE '%" + name + "%'"
                        + " AND startedYear = '" + startedYear +"'";
        List<Curriculum> curriculumList = curriculumRepository.queryHQL(hql);
        
        return (curriculumList.size() == 0) ? null : curriculumList.get(0).getId();
    }
}
