package com.kmutt.stcp.web.report;

import com.kmutt.stcp.web.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Gift on 23-Feb-16.
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        logger.debug("index() is executed!");

        model.put("title", "test");
        model.put("msg", "message test test test");

        List<ReportMaster> records = new ArrayList<>();
        ReportMaster master = new ReportMaster();
        master.setReportName("Courses");
        master.setReportType(ReportType.NORMAL);
        records.add(master);

        master = new ReportMaster();
        master.setReportName("Study Plan");
        master.setReportType(ReportType.NORMAL);
        records.add(master);

        master = new ReportMaster();
        master.setReportName("Summary");
        master.setReportType(ReportType.STAT);
        records.add(master);

        model.put("records",records);


        return "report/report-controller";
    }

//    @RequestMapping(value = "/testTemplate", method = RequestMethod.GET)
//    public String testTemplate(Map<String, Object> model) {
//        return "testTemplate";
//    }
}