package com.kmutt.stcp.web.report;

import com.kmutt.stcp.entity.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Stream;

/**
 * Created by Gift on 20-Mar-16.
 */
public class ReportGenerator {
    private final Logger log = LoggerFactory.getLogger(ReportGenerator.class);

    /**
     * Check authorize & existing report
     * @param userId
     * @param reportId
     * @return
     */
    public boolean isReportValid(Integer userId, Integer reportId) {
        return true;
    }

    public boolean isReportValid(Integer userId, Integer reportId, Integer moduleId) {
        return false;
    }


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
