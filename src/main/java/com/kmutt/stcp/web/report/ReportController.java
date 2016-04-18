package com.kmutt.stcp.web.report;

import com.kmutt.stcp.entity.User;
import com.kmutt.stcp.web.report.bean.SearchReportRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Gift on 23-Feb-16.
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    private User authorizedUser;
    private ReportModule module;
    private String filterText;

    private static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    //index
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayReportList(Map<String, Object> model) {
        logger.debug("index() is executed!");

        Map<String, String> map = new WeakHashMap<>();
        map.put("studentId", "Student ID");
        map.put("staffId", "Staff ID");
        model.put("idOption", map);

        //displayReportList by role
        model.put("records", ReportTemplate.values());

        session().setAttribute("studentId","581");

        return "report/report-controller";
//        return new ResponseEntity<ReportMaster>(master, HttpStatus.OK);
    }


    @RequestMapping(value = "/searchReport", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity searchReport(@RequestBody SearchReportRequest request) {

        ReportTemplate[] searched = Arrays.stream(ReportTemplate.values())
                .filter(elm -> !String.valueOf(elm.ordinal()).equals(request.getReportId()))
                .toArray(ReportTemplate[]::new);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(searched, headers, HttpStatus.OK);
    }

    //onSelectedReport
    @RequestMapping(value = "/reportCenterGenerator", method = RequestMethod.GET, produces = "application/pdf")
    @ResponseBody
    public ResponseEntity<byte[]> reportCenterGenerator(@RequestParam String reportId) {

//        String test = (String) session().getAttribute("studentId");

        ReportGenerator generator = new ReportGenerator();

        Map.Entry<String,Object> e1 = new AbstractMap.SimpleEntry<>("k1","v1");
        Map.Entry<String,Object> e2 = new AbstractMap.SimpleEntry<>("k2","v2");
        byte[] pdfContents = generator.generateReport(0, e1,e2);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("content-disposition", "inline;filename=exported.pdf");

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
    }

    //module clicked
    @RequestMapping(value = "/reportModuleGenerator", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> reportModuleGenerator(@RequestParam String reportId) {
        //TODO pass moduleId

        ReportGenerator generator = new ReportGenerator();
        //FIXME edit arguments
        if(generator.isReportValid(0,2)){
            byte[] pdfContents = generator.generateReport(0,null);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.add("content-disposition", "inline;filename=exported.pdf");

            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
        }

        return null;
    }

}