package com.kmutt.stcp.web.report;

import com.kmutt.stcp.entity.Account;
import com.kmutt.stcp.entity.User;
import com.kmutt.stcp.manager.ReportManager;
import com.kmutt.stcp.web.report.bean.ReportAjaxBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Gift on 23-Feb-16.
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    private static final String SESSION_KEY_LOGIN_ACCOUNT = "loginAccount";
    private static final String SESSION_KEY_LOGIN_USER = "loginUser";
    private static final int ROLE_ID_STUDENT = 1;
    private static final int ROLE_ID_TEACHER = 2;
    private static final int ROLE_ID_ADMIN = 3;

    @Autowired
    private ReportManager reportManager;

    @Autowired
    private ReportGenerator reportGenerator;


    /**
     * index of report center, display report list by user's role
     *
     * @param model view mapping
     * @return path to report center
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayReportList(HttpSession session, Map<String, Object> model) {
        Account loginAccount = (Account) session.getAttribute(SESSION_KEY_LOGIN_ACCOUNT);

        model.put("curriculumNameList", reportManager.findCourses());

        /* displayReportList by role */
        if(ROLE_ID_STUDENT == loginAccount.getRoleUser().getId()) {
            /* student can't view summary planning report */
            ReportTemplate[] reportList = Arrays.stream(ReportTemplate.values())
                    .filter(elm -> !elm.equals(ReportTemplate.SUMMARY_PLANNING))
                    .toArray(ReportTemplate[]::new);
            model.put("reportList", reportList);
        } else {
            /* teacher & admin can't view student planning report */
            ReportTemplate[] reportList = Arrays.stream(ReportTemplate.values())
                    .filter(elm -> !elm.equals(ReportTemplate.STUDENT_PLANNING))
                    .toArray(ReportTemplate[]::new);
            model.put("reportList", reportList);
        }

        return "report/report-controller";
    }

    /**
     * filter report table by search box
     *
     * @param filterText text input
     * @return json data
     */
    @RequestMapping(value = "/searchReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity searchReport(HttpSession session, @RequestParam String filterText) {
        Account loginAccount = (Account) session.getAttribute(SESSION_KEY_LOGIN_ACCOUNT);

        ReportTemplate[] searched;
        if(ROLE_ID_STUDENT == loginAccount.getRoleUser().getId()) {
            /* student can't view summary planning report */
            searched = Arrays.stream(ReportTemplate.values())
                    .filter(elm -> !elm.equals(ReportTemplate.SUMMARY_PLANNING) && elm.getReportName().contains(filterText))
                    .toArray(ReportTemplate[]::new);
        } else {
            /* teacher & admin can't view student planning report */
            searched = Arrays.stream(ReportTemplate.values())
                    .filter(elm -> !elm.equals(ReportTemplate.STUDENT_PLANNING) && elm.getReportName().contains(filterText))
                    .toArray(ReportTemplate[]::new);
        }

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
    public ResponseEntity reportCenterGenerator(HttpSession session, @RequestBody ReportAjaxBean bean) {
        Account loginAccount = (Account) session.getAttribute(SESSION_KEY_LOGIN_ACCOUNT);
        User loginUser = (User) session.getAttribute(SESSION_KEY_LOGIN_USER);

        if(ReportTemplate.STUDENT_PLANNING.ordinal() != bean.getReportId()) {
            //all but STUDENT PLANNER required curriculumName & Year
            if(bean.getCurriculumName() == null && bean.getCurriculumYear() == null) {
                bean.setErrorMsg("กรุณาเลือกหลักสูตรและปีหลักสูตร");
            }
        }

        if(!reportGenerator.isReportValid(loginUser.getId(),bean.getReportId())) {
            bean.setErrorMsg("ไม่มีสิทธิ์ในการใช้งาน");
        }


        //prepare curriculumId
        if(bean.getErrorMsg() == null) {
            bean.setCurriculumId(reportManager.findCourseId(bean.getCurriculumName(), bean.getCurriculumYear()));
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