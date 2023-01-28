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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.headwatersgeographic.trail.entity.Report;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0_TrailSchema.sql", "classpath:flyway/migrations/V1.0_TrailData.sql"},config = @SqlConfig(encoding = "utf-8"))

class CreateReportTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int serverPort;
	
	@Test
	void testThatAReportsisCreatedWhenValidParametersAreSuppliedToTheEndpoint() {
		String body = createReportBody();
		String reportType = "REVIEW";
		String uri = String.format("http://localhost:%d/create_report?report_type= REVIEW&trail_id=3&user_id=2&report_date=2020-01-27 00:00:00&description=I Saw a Bald Eagle", serverPort);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		System.out.println(uri);
		HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
		System.out.println(bodyEntity.toString());
		ResponseEntity<Report> response = restTemplate.exchange(uri,
			    HttpMethod.POST, null, new ParameterizedTypeReference<>() {
				});
		System.out.println(response.toString());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String uri2 = String.format("http://localhost:%d/reports?", serverPort);
		ResponseEntity <List<Report>> response2 = restTemplate.exchange(uri2, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		assertThat(response2.getBody().size()).isEqualTo(7);
	}
	
	protected String createReportBody() { 
		// @formatter:off	
		return "{\n"
				+ "  \"report_type\":\"SPOT_REPORT\",\n"
				+ "  \"trail_id\":3,\n"
				+ "  \"user_id\":2,\n"
				+ "  \"report_date\":2020-01-27 00:00:00\n"
				+ "  \"location\":null,\n"
				+ "  \"description\":\"I Saw a Bald Eagle\",\n"
				+ "}";
		// @formatter:on
		};
}
