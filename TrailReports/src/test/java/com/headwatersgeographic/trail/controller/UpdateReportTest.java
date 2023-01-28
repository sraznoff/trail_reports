package com.headwatersgeographic.trail.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
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
class UpdateReportTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int serverPort;
	
	@Test
	void testThatAReportsisUpdatedWhenValidParametersAreSuppliedToTheEndpoint() {
		int report_id = 3;
		String description = "I found a old glass bottle at the historic dump site.";
		//@formatter:off
//		String body = "{\n"	
//		+" \"report_id\":3,\n"
//		+" \"description\":\"I found a old glass bottle at the historic dump site.\"\n}"
//		;
//		ReportType report_type;
//		Long trail_id;
//		Long user_id;
//		Date report_date;
//		String description;
//		String location;
//		System.out.println(body);
		String uri = String.format("http://localhost:%d/update_report?report_id=%d,description=%s", serverPort, report_id, description);
	//	HttpHeaders headers = new HttpHeaders();
	//	headers.setContentType(MediaType.APPLICATION_JSON);
	//	HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
		ResponseEntity<Report> response = restTemplate.exchange(uri,HttpMethod.PATCH, null, Report.class);
		System.out.println(response.toString());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

	}

	protected String createOrderBody() { 
		// @formatter:off	
		return "{\n"
				+ "  \"customer\":\"VAN_ALTENA_AGNI\",\n"
				+ "  \"model\":\"WRANGLER\",\n"
				+ "  \"trim\":\"Willys Sport\",\n"
				+ "  \"doors\":4,\n"
				+ "  \"color\":\"EXT_NACHO\",\n"
				+ "  \"engine\":\"2_0_TURBO\",\n"
				+ "  \"tire\":\"35_TOYO\",\n"
				+ "  \"options\":[\n"
				+ "    \"DOOR_QUAD_4\",\n"
				+ "    \"EXT_AEV_LIFT\",\n"
				+ "    \"EXT_WARN_WINCH\",\n"
				+ "    \"EXT_WARN_BUMPER_FRONT\",\n"
				+ "    \"EXT_WARN_BUMPER_REAR\",\n"
				+ "    \"EXT_ARB_COMPRESSOR\"\n"
				+ "  ]\n"
				+ "}";
		// @formatter:on
		};
	
}
