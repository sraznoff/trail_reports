package com.headwatersgeographic.trail.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.headwatersgeographic.trail.entity.Report;
import com.headwatersgeographic.trail.entity.ReportType;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class DefaultReportsDao implements ReportsDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	
	@Override
	public List<Report> fetchReports(ReportType reportType, Long trailID, Long userID) {
		String sql =generateSql("Select * from report", reportType, trailID, userID);
		log.info("DAO {}", sql);
		Map<String, Object> params = new HashMap<>();
		if(!(reportType == null)) params.put("report_type", reportType.toString());
		if(!(trailID==null))params.put("trail_id", trailID);
		if(!(userID==null))params.put("user_id", userID);
		System.out.println("hi3 "+  params.toString());
		return jdbcTemplate.query(sql, params, new RowMapper<>() {
			
			@Override
			public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
				log.info("RS {}",rs.toString());
				// @formatter: off
				Report result =  Report.builder()
						.report_id(rs.getLong("report_id"))
						.report_type(ReportType.valueOf(rs.getString("report_type")))
						.trail_id(rs.getLong("trail_id"))
						.user_id(rs.getLong("user_id"))
						.report_date(rs.getString("report_date"))
						.location(rs.getString("location"))
						.description(rs.getString("description"))
						.build();
				log.info(result.getDescription().toString());
				return result;
				// @ formatter: on
			}});
	}

	private String generateSql(String sql, ReportType report_type, Long trail_id, Long user_id) {
		sql +=" where";
		Map<String, Object> params = new HashMap<>();
		params.put("report_type", report_type);
		params.put("trail_id", trail_id);
		params.put("user_id", user_id);
		for(Map.Entry<String,Object> entry : params.entrySet()) {
			if(! (entry.getValue()==null)) sql += String.format(" and %s = :%s", entry.getKey(), entry.getKey());
		}
		sql = sql.replace("where and", "where");
		if (sql.endsWith("where")) sql = sql.substring(0, sql.length()-5);
		return sql;		
	}

	@Override
	public Report deleteReport(Long report_id) {
		log.info("Delete Report DAO");
		String sql = "Delete from report where report_id = :report_id";
		Map<String, Object> params = new HashMap<>();
		params.put("report_id", report_id);
		jdbcTemplate.update(sql, params);
		log.info("Calls made");
		Report result =  Report.builder().report_id(report_id).build();
		log.info(result.toString());
		return result;
				
		
	}

	@Override
	public Report updateReport(Long report_id, ReportType report_type, Long trail_id, Long user_id, String report_date,
			String description, String location) {
		String sql = "Update report set";
		log.info("DAO {}", sql);
		Map<String, Object> params = new HashMap<>();
		params.put("report_id", report_id);
		if(report_type == null && trail_id == null && user_id == null && report_date == null && description == null && location == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requests must include at leas one parameter to update");
		if(!(report_type == null)) {
			params.put("report_type", report_type.toString());
			sql += " report_type = :report_type,";
		}
		if(!(trail_id==null)) {
			params.put("trail_id", trail_id);
			sql+= " trail_id = :trail_id,";
		}
		if(!(user_id==null)) {
			params.put("user_id", user_id);
			sql+= " user_id = :user_id,";
		}
		if(!(report_date==null)) {
			params.put("report_date", report_date.toString());
			sql+= " report_date = :report_date,";
		}
		if(!(description==null)) {
			log.info("Hey it found desc. {}",description);
			params.put("description", description);
			sql+= " description = :description,";
		}
		if(!(location==null)) {
			params.put("location", location);
			sql+= " location = :location,";
		}
		sql+= " where report_id = :report_id";
		sql = sql.replace(", where", " where");
		
		jdbcTemplate.update(sql, params);
		//@formatter:off
		Report result =  Report.builder()
				.report_id(report_id)
				.report_type(report_type)
				.trail_id(trail_id)
				.user_id(user_id)
				.report_date(report_date)
				.location(location)
				.description(description)
				.build();
		//@formatter:on
		log.info(result.getDescription().toString());
		return result;

			
	}

	@Override
	public Report createReport(ReportType report_type, Long trail_id, Long user_id, String report_date,
			String description, String location) {
		log.info("Create Report DAO");
		String sql = "Insert into report (report_type, trail_id, user_id, report_date, location, description) VALUES (:report_type, :trail_id, :user_id, :report_date, :location, :description)";
		MapSqlParameterSource params = new  MapSqlParameterSource();
		params.addValue("report_type", report_type.toString());
		params.addValue("trail_id", trail_id);
		params.addValue("user_id", user_id);
		params.addValue("report_date", report_date);
		params.addValue("description", description);
		params.addValue("location", location);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(sql, params, keyHolder);
		Long reportPK = keyHolder.getKey().longValue();
		//@formatter:off
		Report result = Report.builder()
				.report_id(reportPK)
				.report_type(report_type)
				.trail_id(trail_id)
				.user_id(user_id)
				.report_date(report_date)
				.location(location)
				.description(description)
				.build();
		//@formatter:on
		return result;

	}
}
