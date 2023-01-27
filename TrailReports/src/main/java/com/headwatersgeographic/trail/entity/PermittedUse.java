package com.headwatersgeographic.trail.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermittedUse {
	
	private Long use_id;
	private String use_name;
	private String transportation_mode;
	private String dates_allowed;

}
