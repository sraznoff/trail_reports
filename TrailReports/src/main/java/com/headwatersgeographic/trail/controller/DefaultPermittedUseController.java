package com.headwatersgeographic.trail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.headwatersgeographic.trail.entity.PermittedUse;
import com.headwatersgeographic.trail.service.PermittedUseService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultPermittedUseController implements PermittedUseController{
	
	@Autowired
	private PermittedUseService permittedUseService;

	@Override
	public List<PermittedUse> fetchPermittedUse(Long trail_id) {
		log.info("controller");
		return permittedUseService.fetchPermittedUse(trail_id);
	}



}
