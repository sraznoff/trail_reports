package com.headwatersgeographic.trail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headwatersgeographic.trail.dao.UserDao;
import com.headwatersgeographic.trail.entity.User;

@Service
public class DefaultUserService implements UserService{

	@Autowired
	private UserDao userDao;
	@Override
	public List<User> fetchUsers() {
		return userDao.fetchUsers();
	}

}
