package com.headwatersgeographic.trail.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.headwatersgeographic.trail.entity.User;

@Component
public class DefaultUserDao implements UserDao{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Override
	public List<User> fetchUsers() {
		String sql = "select * from trail_user";
		return jdbcTemplate.query(sql, new RowMapper<>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException{
				User result = User.builder().first_name(rs.getString("first_name")).last_name(rs.getString("last_name")).username(rs.getString("username")).build();
				return result;
			}
		});
				
				
				
	}

}
