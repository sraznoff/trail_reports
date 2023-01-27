package com.headwatersgeographic.trail.dao;

import java.util.List;
import com.headwatersgeographic.trail.entity.Report;
import com.headwatersgeographic.trail.entity.ReportType;

public interface ReportsDao {
	//@return
	List<Report> fetchReports(ReportType reportType, Long trailID, Long userID);

	Report deleteReport(Long report_id);



}
