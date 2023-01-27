package com.headwatersgeographic.trail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.headwatersgeographic.trail.dao.PermittedUseDao;
import com.headwatersgeographic.trail.entity.PermittedUse;

@Service
public class DefaultPermittedUseService implements PermittedUseService {
	@Autowired
	private PermittedUseDao permittedUseDao;
	
	@Override
	public List<PermittedUse> fetchPermittedUse(Long trail_id) {
		return permittedUseDao.fetchPermittedUse(trail_id);
	}
}	
