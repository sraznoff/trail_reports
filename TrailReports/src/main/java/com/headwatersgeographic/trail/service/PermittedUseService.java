package com.headwatersgeographic.trail.service;

import java.util.List;

import com.headwatersgeographic.trail.entity.PermittedUse;

public interface PermittedUseService {

	List<PermittedUse> fetchPermittedUse(Long trail_id);

}
