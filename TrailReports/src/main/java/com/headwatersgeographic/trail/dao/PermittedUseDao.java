package com.headwatersgeographic.trail.dao;

import java.util.List;

import com.headwatersgeographic.trail.entity.PermittedUse;

public interface PermittedUseDao {

	List<PermittedUse> fetchPermittedUse(Long trail_id);

}
