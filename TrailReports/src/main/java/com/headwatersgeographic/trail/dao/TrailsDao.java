package com.headwatersgeographic.trail.dao;

import java.util.List;

import com.headwatersgeographic.trail.entity.Trail;

public interface TrailsDao {
	//@return
//	List<Trail> fetchTrails();

	List<Trail> fetchTrails(String permittedUse);

//	List<Trail> fetchTrailsByUse(String permittedUse);

}
