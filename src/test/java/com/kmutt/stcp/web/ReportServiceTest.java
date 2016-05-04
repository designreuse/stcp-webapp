package com.kmutt.stcp.web;

import com.kmutt.stcp.config.SpringRootConfig;
import com.kmutt.stcp.config.SpringWebConfig;
import com.kmutt.stcp.manager.ReportManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by jirapatj on 5/1/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class, SpringWebConfig.class})
@WebAppConfiguration
public class ReportServiceTest {

    @Autowired
    ReportManager reportManager;

    @Test
    public void testGenerateReport() throws Exception {
        System.out.println(reportManager);
    }

//    @Test
//    public void testIsReportValid() throws Exception {
//
//    }
//
//    @Test
//    public void testDisplayReportList() throws Exception {
//
//    }
//
//    @Test
//    public void testReportCenterGenerator() throws Exception {
//
//    }
//
//    @Test
//    public void testSearchReport() throws Exception {
//
//    }


}
