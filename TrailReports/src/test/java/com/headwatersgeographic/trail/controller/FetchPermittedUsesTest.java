package com.headwatersgeographic.trail.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.headwatersgeographic.trail.entity.PermittedUse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0_TrailSchema.sql", "classpath:flyway/migrations/V1.0_TrailData.sql"},config = @SqlConfig(encoding = "utf-8"))

class FetchPermittedUsesTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int serverPort;
	
	@Test
	void testThatAllPermittedUsesAreReturned() {
		String uri = String.format("http://localhost:%d/permitted_use?", serverPort);
		ResponseEntity<List<PermittedUse>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {	});
		assertThat(response.getBody()).isNotEmpty();
	}
	@Test
	void testThatSomePermittedUsesAreReturnedWhenATrailIsSpecified() {
		int trailID = 2;
		String uri = String.format("http://localhost:%d/permitted_use?trail_id=%d", serverPort, trailID);
		ResponseEntity<List<PermittedUse>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {	});
		assertThat(response.getBody()).isNotEmpty();
	}
}
