package com.kmutt.stcp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by jirapatj on 2/21/16.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        logger.debug("index() is executed!");

        model.put("title", "test");
        model.put("msg", "message test test test");

        return "test";
    }

    @RequestMapping(value = "/testTemplate", method = RequestMethod.GET)
    public String testTemplate(Map<String, Object> model) {
        return "testTemplate";
    }
    
//    @RequestMapping(value = "/courseofferring", method = RequestMethod.GET)
//    public String courseOfferring(Map<String, Object> model) {
//        return "courseofferring";
//    }
    
    
    
    
    
    
}
