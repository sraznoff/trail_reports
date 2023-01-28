package com.headwatersgeographic.trail.dao;

import java.util.List;
import com.headwatersgeographic.trail.entity.Report;
import com.headwatersgeographic.trail.entity.ReportType;

public interface ReportsDao {
	//@return
	List<Report> fetchReports(ReportType reportType, Long trailID, Long userID);

	Report deleteReport(Long report_id);

	Report updateReport(Long report_id, ReportType report_type, Long trail_id, Long user_id, String report_date,
			String description, String location);

	Report createReport(ReportType report_type, Long trail_id, Long user_id, String report_date, String description,
			String location);



}
