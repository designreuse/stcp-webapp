package com.kmutt.stcp.web;

import com.kmutt.stcp.config.SpringRootConfig;
import com.kmutt.stcp.config.SpringWebConfig;
import com.kmutt.stcp.manager.ReportManager;
import com.kmutt.stcp.web.report.ReportGenerator;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jirapatj on 5/1/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringRootConfig.class, SpringWebConfig.class})
@WebAppConfiguration
public class ReportServiceTest {

    @Autowired
    private ReportManager reportManager;

    @Autowired
    private ReportGenerator reportGenerator;

    @Ignore
    @Test
    public void testGenerateReport() throws Exception {
        try{
            byte[] report = reportGenerator.generateReport(null, null);
            assertFalse(report.length > 0 );
        }catch(NullPointerException ex){
            System.out.println("ERROR NULL");
            assertTrue(true);
        }catch (Exception e) {
            System.out.println("ERROR");
            assertTrue(false);
        }
    }

    @Ignore
    @Test
    public void testGenerateReportValid() throws Exception {
        try{
            Map.Entry<String, Object> pairParam;
            pairParam = new AbstractMap.SimpleEntry<>("ID_CURRICULUM", 1);
            byte[] report = reportGenerator.generateReport(3,pairParam);
            assertTrue(report.length > 0 );
        }catch(NullPointerException ex){
            System.out.println("ERROR NULL");
            assertFalse(true);
        }catch (Exception e) {
            System.out.println("ERROR");
            assertFalse(true);
        }
    }

    @Ignore
    @Test
    public void testIsReportValid() throws Exception {
        assertTrue(reportGenerator.isReportValid(1,1));
    }

}