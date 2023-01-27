package com.headwatersgeographic.trail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.headwatersgeographic.trail.entity.Trail;
import com.headwatersgeographic.trail.service.TrailsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultTrailsController implements TrailsController{
	
	@Autowired
	private TrailsService trailsService;
	

//	@Override
//	public List<Trail> fetchTrails() {
//		log.info("Controller");
//		return trailsService.fetchTrails();
//	}


	@Override
	public List<Trail> fetchTrailsByUse(String permittedUse) {
		log.info("controller");
		return trailsService.fetchTrails(permittedUse);
	}

}
