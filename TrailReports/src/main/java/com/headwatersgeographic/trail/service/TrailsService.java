package com.headwatersgeographic.trail.service;

import java.util.List;

import com.headwatersgeographic.trail.entity.Trail;

public interface TrailsService {

	List<Trail> fetchTrails(String permittedUse);

}
