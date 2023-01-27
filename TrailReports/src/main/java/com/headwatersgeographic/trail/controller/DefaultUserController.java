package com.headwatersgeographic.trail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.headwatersgeographic.trail.entity.User;
import com.headwatersgeographic.trail.service.UserService;

@RestController
public class DefaultUserController implements UserController{

	@Autowired
	private UserService userService;
	
	@Override
	public List<User> fetchUsers() {
		return userService.fetchUsers();
	}

}
