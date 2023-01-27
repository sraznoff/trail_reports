package com.headwatersgeographic.trail.dao;

import java.util.List;

import com.headwatersgeographic.trail.entity.User;

public interface UserDao {

	List<User> fetchUsers();

}
