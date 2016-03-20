package com.kmutt.stcp.web.report;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

public interface ReportInterface {
    //TODO module call me
//    boolean isReportValid(Integer reportId);
    byte[] generateReport(Integer reportId, Map.Entry<String, Object>...paramValues);
}
