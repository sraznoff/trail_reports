package com.headwatersgeographic.trail.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private Long user_id;
	private String first_name;
	private String last_name;
	private String username;

}
