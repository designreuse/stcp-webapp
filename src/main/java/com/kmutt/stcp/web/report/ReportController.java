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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        logger.debug("index() is executed!");

        Map<String, String> map = new WeakHashMap<>();
        map.put("studentId", "Student ID");
        map.put("staffId", "Staff ID");
        model.put("idOption", map);

        //displayReportList by role
        model.put("records", ReportTemplate.values());

        return "report/report-controller";
//        return new ResponseEntity<ReportMaster>(master, HttpStatus.OK);
    }


    @RequestMapping(value = "/searchReport", method = RequestMethod.POST)
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
    @RequestMapping(value = "/reportCenterGenerator", method = RequestMethod.GET)
    public ResponseEntity<byte[]> reportCenterGenerator(Map<String, Object> model) {
        ReportGenerator generator = new ReportGenerator();
//        r.generateReport(0,null);

        // convert JSON to Employee
//        Employee emp = convertSomehow(json);
//
//        // generate the file
//        PdfUtil.showHelp(emp);

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
    @RequestMapping(value = "/reportModuleGenerator", method = RequestMethod.GET)
    public ResponseEntity<byte[]> reportModuleGenerator(/*@RequestBody String json*/) {
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