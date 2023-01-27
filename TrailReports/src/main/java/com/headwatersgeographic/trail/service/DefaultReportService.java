package com.headwatersgeographic.trail.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headwatersgeographic.trail.dao.ReportsDao;
import com.headwatersgeographic.trail.entity.Report;
import com.headwatersgeographic.trail.entity.ReportType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultReportService implements ReportService{

	@Autowired
	private ReportsDao reportDao;

	@Override
	public List<Report> fetchReports(ReportType reportType, Long trailID, Long userID) {
		log.info("reportdao");
		return reportDao.fetchReports(reportType, trailID, userID);
	}

	@Override
	public Report deleteReport(Long report_id) {
		log.info("Delete Report Service");
		return reportDao.deleteReport(report_id);
	}


}