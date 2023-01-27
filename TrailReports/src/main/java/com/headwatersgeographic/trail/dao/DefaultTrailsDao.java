package com.headwatersgeographic.trail.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.headwatersgeographic.trail.entity.Trail;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class DefaultTrailsDao implements TrailsDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Trail> fetchTrails(String permittedUse) {
		log.info("PMDAO");
		String sql ="";
		if (permittedUse == null) {
			//@formatter: off
			sql = ""
				+"Select * from trail";
			//formatter: on
		}
		else {
		//@formatter: off
		sql = ""
			+"Select t.* from trail t"
			+" inner join trail_permitted_use tpu"
			+" on t.trail_id = tpu.trail_id"
			+" inner join permitted_use pu" 
			+" on tpu.use_id = pu.use_id"
			+" where pu.use_name = :permittedUse";
		//@formatter: on
		}
		Map<String, Object> params = new HashMap<>();
		if (!(permittedUse == null)) params.put("permittedUse", permittedUse.toString());
	
		return jdbcTemplate.query(sql, params, new RowMapper<>() {

			@Override
			public Trail mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				// @formatter: off
				Trail result =  Trail.builder()
						.trail_name(rs.getString("trail_name"))
						.length_mi(rs.getBigDecimal("length_mi"))
						.difficulty(rs.getInt("difficulty"))
						.location(rs.getString("location"))
						.build();
				log.info(result.toString());
				return result;
				// @ formatter: on
			}});
	}

//
//	@Override
//	public List<Trail> fetchTrails(String permittedUse) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

}
