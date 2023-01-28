package com.headwatersgeographic.trail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.headwatersgeographic.trail.entity.Report;
import com.headwatersgeographic.trail.entity.ReportType;
import com.headwatersgeographic.trail.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DefaultReportController implements ReportController {

	@Autowired
	private ReportService reportService;
	
	@Override
	public List<Report> fetchReports(ReportType report_type, Long trail_id, Long user_id) {
		log.info("DRC {}", report_type);
		return reportService.fetchReports(report_type, trail_id, user_id);
	}

	@Override
	public Report deleteReport(Long report_id) {
		log.info("Delete Report Controller");
		return reportService.deleteReport(report_id);
	}

	@Override
	public Report updateReport(Long report_id, ReportType report_type, Long trail_id, Long user_id, String report_date,
			String description, String location) {
		return reportService.updateReport(report_id, report_type, trail_id, user_id, report_date,
				description, location);
	}

	@Override
	public Report createReport(ReportType report_type, Long trail_id, Long user_id, String report_date,
			String description, String location) {
		return reportService.createReport(report_type, trail_id, user_id, report_date, description, location);
	}

}
