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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.headwatersgeographic.trail.entity.Report;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0_TrailSchema.sql", "classpath:flyway/migrations/V1.0_TrailData.sql"},config = @SqlConfig(encoding = "utf-8"))
class DeleteReportTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int serverPort;
	
	@Test
	void testThatAReportsisDeletedwhenAValidReportIdIsSuppliedToTheEndpoint() {
		int report_id = 3;
		String uri = String.format("http://localhost:%d/delete_report?report_id=%d", serverPort, report_id);
		ResponseEntity<Report> response = restTemplate.exchange(uri, HttpMethod.DELETE, null, Report.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String uri2 = String.format("http://localhost:%d/reports?", serverPort);
		ResponseEntity <List<Report>> response2 = restTemplate.exchange(uri2, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
		assertThat(response2.getBody().size()).isEqualTo(5);
	}
}
