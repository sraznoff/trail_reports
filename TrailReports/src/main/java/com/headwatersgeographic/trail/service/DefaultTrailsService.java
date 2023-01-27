package com.headwatersgeographic.trail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headwatersgeographic.trail.dao.TrailsDao;
import com.headwatersgeographic.trail.entity.Trail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultTrailsService implements TrailsService{

	@Autowired
	private TrailsDao trailsDao;

	@Override
	public List<Trail> fetchTrails(String permittedUse) {
		log.info("PM Service");
		return trailsDao.fetchTrails(permittedUse);
	}

}
