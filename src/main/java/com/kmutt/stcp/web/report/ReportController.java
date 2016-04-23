package com.kmutt.stcp.web.report;

import com.kmutt.stcp.entity.User;
import com.kmutt.stcp.web.report.bean.ReportAjaxBean;
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
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Gift on 23-Feb-16.
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    private User authorizedUser;

    private static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    /**
     * index of report center
     *
     * @param model view mapping
     * @return path to report center
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayReportList(Map<String, Object> model) {
        logger.debug("index() is executed!");

        Map<String, String> map = new WeakHashMap<>();
        map.put("studentId", "Student ID");
        map.put("staffId", "Staff ID");
        model.put("idOption", map);

        //displayReportList by role
        model.put("reportList", ReportTemplate.values());

        return "report/report-controller";
//        return new ResponseEntity<ReportMaster>(master, HttpStatus.OK);
    }

    /**
     * filter report table by search box
     *
     * @param filterText text input
     * @return json data
     */
    @RequestMapping(value = "/searchReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity searchReport(@RequestParam String filterText) {

        ReportTemplate[] searched = Arrays.stream(ReportTemplate.values())
                .filter(elm -> elm.getReportName().contains(filterText))
                .toArray(ReportTemplate[]::new);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(searched, headers, HttpStatus.OK);
    }

    /**
     * check required parameter for generating report (from report center)
     * @param bean ajax request bean
     * @return ajax response with errorMsg if something wrong
     */
    @RequestMapping(value = "/reportCenterGenerator", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity reportCenterGenerator(@RequestBody ReportAjaxBean bean) {

        //STUDENT_PLANNING needs userId, othewise courseId
        //กรุณาเลือก courseId
        ReportGenerator gen = new ReportGenerator();
        if(!gen.isReportValid(authorizedUser.getId(),bean.getReportId())) {
            bean.setErrorMsg("ไม่มีสิทธิ์ในการใช้งาน");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(bean, headers, HttpStatus.OK);
    }

    /**
     * check required parameter for generating report (from module)
     * @param bean ajax request bean
     * @return ajax response
     */
    @RequestMapping(value = "/reportModuleGenerator", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity reportModuleGenerator(@RequestBody ReportAjaxBean bean) {

        //TODO reportCenterGenerator

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(bean, headers, HttpStatus.OK);
    }

    /**
     * on selected report from report center's table,
     * generate PDF report
     *
     * @param reportId unique value for report
     * @return pdf media
     */
    @RequestMapping(value = "/reportCenterGenerator/pdf", method = RequestMethod.GET, produces = "application/pdf")
    @ResponseBody
    public ResponseEntity<byte[]> reportCenterGeneratorPDF(@RequestParam int reportId) {

        ReportGenerator generator = new ReportGenerator();

        //TODO isReportValid

        //FIXME edit parameters
        Map.Entry<String, Object> e1 = new AbstractMap.SimpleEntry<>("k1", "v1");
        Map.Entry<String, Object> e2 = new AbstractMap.SimpleEntry<>("k2", "v2");
        byte[] pdfContents = generator.generateReport(reportId, e1, e2);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("content-disposition", "inline;filename=exported.pdf");

//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
    }

 /*   *//**
     * for others module linked for report,
     * generate PDF report
     *
     * @param reportId unique value for report
     * @return pdf media
     *//*
    @RequestMapping(value = "/reportModuleGenerator/pdf", method = RequestMethod.GET, produces = "application/pdf")
    @ResponseBody
    public ResponseEntity<byte[]> reportModuleGeneratorPDF(@RequestParam int reportId) {
        //TODO pass moduleId

        ReportGenerator generator = new ReportGenerator();
        //FIXME edit arguments
        if (generator.isReportValid(0, 2)) {
            byte[] pdfContents = generator.generateReport(0);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.add("content-disposition", "inline;filename=exported.pdf");

//            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
        }

        return null;
    }*/

}