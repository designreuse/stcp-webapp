package com.kmutt.stcp.web.report;

import com.kmutt.stcp.web.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        return "report/report-controller";
    }

//    @RequestMapping(value = "/testTemplate", method = RequestMethod.GET)
//    public String testTemplate(Map<String, Object> model) {
//        return "testTemplate";
//    }
}