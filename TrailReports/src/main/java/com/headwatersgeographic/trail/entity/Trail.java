package com.headwatersgeographic.trail.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trail {
	
	private String trail_name;
	private BigDecimal length_mi;
	private int difficulty;
	private String location;

}
