package com.kmutt.stcp.manager;

import com.kmutt.stcp.entity.Account;
import com.kmutt.stcp.entity.Curriculum;
import com.kmutt.stcp.entity.RoleUser;
import com.kmutt.stcp.repository.AccountRepository;
import com.kmutt.stcp.repository.CurriculumRepository;
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
    @Autowired
    private AccountRepository accountRepository;

    public List<Curriculum> findDistinceCurriculums() {
        String hql = "SELECT c FROM Curriculum c GROUP BY c.name";
        List<Curriculum> curriculumResult = curriculumRepository.queryHQL(hql);
        return (curriculumResult.size() == 0) ? null : curriculumResult;
    }

    public Integer findCurriculumId(String name, String startYear) {
        String hql = "SELECT c FROM Curriculum c"
                + " WHERE name = '" + name + "'"
                + " AND startYear = '" + startYear +"'";
        List<Curriculum> curriculumResult = curriculumRepository.queryHQL(hql);
        return (curriculumResult.size() == 0) ? null : curriculumResult.get(0).getId();
    }

    public RoleUser findRoleUser(Integer userId) {
        String hql = "SELECT a FROM Account a"
                + " LEFT JOIN FETCH a.roleUser"
                + " WHERE a.user.id = " + userId;
        List<Account> accountResults = accountRepository.queryHQL(hql);
        return (accountResults.size() == 0) ? null : accountResults.get(0).getRoleUser();
    }
}
