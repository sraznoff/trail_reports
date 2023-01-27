package com.headwatersgeographic.trail.service;

import java.util.List;
import com.headwatersgeographic.trail.entity.Report;
import com.headwatersgeographic.trail.entity.ReportType;

public interface ReportService {
	List<Report> fetchReports(ReportType report_type, Long trail_id, Long user_id);

	Report deleteReport(Long report_id);

}
