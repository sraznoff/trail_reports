package com.headwatersgeographic.trail.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
	
	private Long report_id;
	private ReportType report_type;
	private Long trail_id;
	private Long user_id;
	private Date report_date;
	private String description;
	private String location;

}
