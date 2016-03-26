package com.kmutt.stcp.web.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Gift on 20-Mar-16.
 */
public class ReportGenerator {
    private final Logger log = LoggerFactory.getLogger(ReportGenerator.class);

    public boolean isReportValid(Integer reportId) {
        return true;
    }

    public boolean isReportValid(Integer reportId, Integer moduleId) {
        return false;
    }

    public byte[] generateReport(Integer reportId, Map.Entry<String, Object>... paramValues) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("report-templates/Student_Planning.jrxml");
            JasperDesign jrDesign = JRXmlLoader.load(in);

            // Compile jrxml file.
            JasperReport jasperReport = JasperCompileManager.compileReport(jrDesign);

            // Parameters for report
            Map<String, Object> parameters = new WeakHashMap<>();
//            paramValues.
            parameters.put("gifttestparam", "MEMEME");
            // DataSource
            // This is simple example, no database.
            // then using empty datasource.
            JRDataSource dataSource = new JREmptyDataSource();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            log.debug("report generated");

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
        }

        return null;
    }
}
