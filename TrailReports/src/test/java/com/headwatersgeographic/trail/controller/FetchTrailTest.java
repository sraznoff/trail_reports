package com.headwatersgeographic.trail.controller;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.headwatersgeographic.trail.entity.Trail;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0_TrailSchema.sql", "classpath:flyway/migrations/V1.0_TrailData.sql"},config = @SqlConfig(encoding = "utf-8"))
class FetchTrailTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int serverPort;
	
	@Test
	void testThatATrailIsReturnedWhenNoParametersAreSuppliedToTrailEndpoint() {
		String uri = String.format("http://localhost:%d/trails?", serverPort);
		ResponseEntity<List<Trail>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotEmpty();
	}
	@Test
	void testThatTheCorrectTrailsAreReturnedWhenAUseModeIsSupplied() {
		String permittedUse = "horseback";
		String uri = String.format("http://localhost:%d/trails?use=%s", serverPort, permittedUse);
		System.out.println("hi");
		ResponseEntity<List<Trail>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		//System.out.println(response.toString());
		List<Trail> expected = buildExpected();
		
		assertThat(response.getBody().get(0).getTrail_name()).isEqualTo(expected.get(0).getTrail_name());
	}
	
	private List<Trail> buildExpected() {
		List<Trail> list = new LinkedList<>();
		//formatter:off
		list.add(Trail.builder()
				.trail_name("Show-Me-The-Horse Trail")
				.build());
		
		//formatter:on
		return list;
	}
	/*
	@Test
	void testThatAValidCodeIsReturnedWhenAValidModelAndTrimAreSupplied() {
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Sport";
		String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
		ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println(response.toString());
	}
	*/
	
	/*
	@Test
	void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied(){
		//Given 
		JeepModel model = JeepModel.WRANGLER;
		String trim = "Sport";
		String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
		//When
		ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		//Then
		List<Jeep> expected = buildExpected();
		assertThat(response.getBody()).isEqualTo(expected);
	}
	private List<Jeep> buildExpected() {
		List<Jeep> list = new LinkedList<>();
		//formatter:off
		list.add(Jeep.builder()
				.modelID(JeepModel.WRANGLER)
				.trimLevel("Sport")
				.numDoors(2)
				.wheelSize(17)
				.basePrice(new BigDecimal("28475.00"))
				.build());

		list.add(Jeep.builder()
				.modelID(JeepModel.WRANGLER)
				.trimLevel("Sport")
				.numDoors(4)
				.wheelSize(17)
				.basePrice(new BigDecimal("31975.00"))
				.build());
		
		//formatter:on
		return list;
	}
	*/


}
