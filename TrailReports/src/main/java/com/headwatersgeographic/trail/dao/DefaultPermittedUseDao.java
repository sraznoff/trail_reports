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

import com.headwatersgeographic.trail.entity.PermittedUse;

@Component
public class DefaultPermittedUseDao implements PermittedUseDao{
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<PermittedUse> fetchPermittedUse(Long trail_id){
		String sql = "";
		if (trail_id == null) sql="Select * from permitted_use";
		else{
			//@formatter: off
			sql = ""
				+"Select pu.* from trail t"
				+" inner join trail_permitted_use tpu"
				+" on t.trail_id = tpu.trail_id"
				+" inner join permitted_use pu" 
				+" on tpu.use_id = pu.use_id"
				+" where t.trail_id = :trail_id";
			//@formatter: on
			}
		Map<String, Object> params = new HashMap<>();
		if (!(trail_id == null)) params.put("trail_id", trail_id.toString());
		return jdbcTemplate.query(sql,  params, new RowMapper<>() {
			@Override
			public PermittedUse mapRow(ResultSet rs, int rowNum) throws SQLException{
				//@formatter:off
				PermittedUse result = PermittedUse.builder()
						.use_id(rs.getLong("use_id"))
						.use_name(rs.getString("use_name"))
						.transportation_mode("transportation_mode")
						.dates_allowed("dates_allowed")
						.build();
				return result;
				//@formatter:on
			}
		});
		
	}
	
	

}
