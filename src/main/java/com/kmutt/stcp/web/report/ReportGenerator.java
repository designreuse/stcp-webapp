package com.kmutt.stcp.web.report;

import com.kmutt.stcp.entity.RoleUser;
import com.kmutt.stcp.entity.User;
import com.kmutt.stcp.manager.ReportManager;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Stream;

/**
 * Created by Gift on 20-Mar-16.
 */
@Service
public class ReportGenerator {
    private final Logger log = LoggerFactory.getLogger(ReportGenerator.class);

    protected static final int ROLE_ID_STUDENT = 1;
    protected static final int ROLE_ID_TEACHER = 2;
    protected static final int ROLE_ID_ADMIN = 3;

    @Autowired
    ReportManager reportManager;
    /**
     * Check authorize & existing report
     * @param userId
     * @param reportId
     * @return
     */
    public boolean isReportValid(Integer userId, Integer reportId) {
        RoleUser roleUser = reportManager.findRoleUser(userId);
        switch (roleUser.getId()) {
            case ROLE_ID_STUDENT:
                return ReportTemplate.SUMMARY_PLANNING.ordinal() != reportId;
            case ROLE_ID_TEACHER:
            case ROLE_ID_ADMIN:
                return ReportTemplate.STUDENT_PLANNING.ordinal() != reportId;
            default:
                return false;
        }
    }

//    public boolean isReportValid(Integer userId, Integer reportId, Integer moduleId) {
//        return false;
//    }

    @SafeVarargs
    public final byte[] generateReport(Integer reportId, Map.Entry<String, Object>... paramValues) {
        try {
            ReportTemplate template = ReportTemplate.values()[reportId];
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("report-templates/"+template.getReportTemplateName());
            JasperDesign jrDesign = JRXmlLoader.load(in);

            // Compile jrxml file.
            JasperReport jasperReport = JasperCompileManager.compileReport(jrDesign);

            // Parameters for report
            Map<String, Object> reportParams = new WeakHashMap<>();
            if(paramValues != null) {
                Arrays.stream(paramValues).forEach(entry -> reportParams.put(entry.getKey(), entry.getValue()));
            }

//            reportParams.putAll(paramValues);
            // DataSource
            // This is simple example, no database.
            // then using empty datasource.
            JRDataSource dataSource = new JREmptyDataSource();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, dataSource);


            log.debug("report generated");

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            log.error("generate report error", e);
        }

        return null;
    }
}
