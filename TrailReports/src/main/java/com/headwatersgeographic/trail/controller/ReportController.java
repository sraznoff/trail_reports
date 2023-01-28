package com.headwatersgeographic.trail.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.headwatersgeographic.trail.entity.Report;
import com.headwatersgeographic.trail.entity.ReportType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@OpenAPIDefinition(info = @Info(title="Report Service"), servers = {@Server(url="http://localhost:8080", description = "Local Server.")})
public interface ReportController {
	@RequestMapping(value="/reports",method = RequestMethod.GET)
	//@formatter:off
	@Operation(
			summary = "Returns a list of reports",
			description = "Returns a list of reports given optional parameters of report_type, trail_id or user_id.",
			responses= {
					@ApiResponse(
							responseCode = "200", 
							description = "A list of reports is returned.", 
							content = @Content(
									mediaType = "application/json", 
									schema = @Schema(implementation = Report.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No reports were found with the input criteria.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occured.", 
							content = @Content(mediaType = "application/json"))	
				},
					parameters = {
							@Parameter(
									name = "report_type", 
									allowEmptyValue = false, 
									required = false, 
									description = "The type of reports you want to see"
									),
							@Parameter(
									name = "trail_id", 
									allowEmptyValue = true, 
									required = false, 
									description = "The trail for which you want to see reports"
									),
							@Parameter(
									name = "user_id", 
									allowEmptyValue = false, 
									required = false, 
									description = "The author who's reports you want to see"
									)
					}
			)
	//@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	
List<Report> fetchReports(
			@RequestParam(required = false) ReportType report_type, 
			@RequestParam(required = false) Long trail_id,
			@RequestParam(required = false) Long user_id
	);
	
	
	
	@Operation(
			summary = "Deletes a Report by ReportID",
			description = "Deletes a report by report id.",
			responses= {
					@ApiResponse(
							responseCode = "200", 
							description = "A report has been deleted.", 
							content = @Content(
									mediaType = "application/json", 
									schema = @Schema(implementation = Report.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No reports were found with the input criteria.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occured.", 
							content = @Content(mediaType = "application/json"))	
				},
					parameters = {
							@Parameter(
									name = "report_id", 
									allowEmptyValue = false, 
									required = false, 
									description = "The id of the report you want to delete"
									)
					}
			
			)
	@RequestMapping(value="/delete_report",method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.OK)
	Report deleteReport(@RequestParam(required = true) Long report_id);
	
	@Operation(
			summary = "Updates a report",
			description = "Update a report by supplying a reportid and any parameters you want to update as well.",
			responses= {
					@ApiResponse(
							responseCode = "200", 
							description = "A report has been updated.", 
							content = @Content(
									mediaType = "application/json", 
									schema = @Schema(implementation = Report.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No reports were found with the input criteria.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occured.", 
							content = @Content(mediaType = "application/json"))	
				},
					parameters = {
							@Parameter(
									name = "report_id", 
									allowEmptyValue = false, 
									required = true, 
									description = "The id of the report you want to update"
									),
							@Parameter(
									name = "report_type", 
									allowEmptyValue = false, 
									required = false, 
									description = "The type of report this should be. Null values will be ignored/unchanged."
									),
							@Parameter(
									name = "trail_id", 
									allowEmptyValue = false, 
									required = false, 
									description = "Update the trail this report is associated with.  Null values will be ignored/unchanged."
									),
							@Parameter(
									name = "user_id", 
									allowEmptyValue = false, 
									required = false, 
									description = "The id of the author of this report,  Null values will be ignored/unchanged."
									),
							@Parameter(
									name = "report_date", 
									allowEmptyValue = false, 
									required = false, 
									description = "Update the date of the report.  Null values will be ignored/unchanged."
									),
							@Parameter(
									name = "location", 
									allowEmptyValue = false, 
									required = false, 
									description = "The location of the report if it is a spot report.  Null values will be ignored/unchanged."
									),
							@Parameter(
									name = "description", 
									allowEmptyValue = false, 
									required = false, 
									description = "Update the description of the report.  Null values will be ignored/unchanged."
									)
							
					}
			
			)
	@RequestMapping(value="/update_report",method = RequestMethod.PATCH)
	@ResponseStatus(code = HttpStatus.OK)
	Report updateReport(
			//@formatter:off
			@RequestParam(required = true) Long report_id,
			@RequestParam(required = false) ReportType report_type,
			@RequestParam(required = false) Long trail_id,
			@RequestParam(required = false) Long user_id,
			@RequestParam(required = false) String report_date,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String location
			//@formatter:on
	);
	
	@Operation(
			summary = "Create a report",
			description = "Create a report by supplying required parameters and any optional you want to include as well.",
			responses= {
					@ApiResponse(
							responseCode = "200", 
							description = "A report has been created.", 
							content = @Content(
									mediaType = "application/json", 
									schema = @Schema(implementation = Report.class))),
					@ApiResponse(
							responseCode = "400", 
							description = "The request parameters are invalid.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "404", 
							description = "No reports were found with the input criteria.", 
							content = @Content(mediaType = "application/json")),
					@ApiResponse(
							responseCode = "500", 
							description = "An unplanned error occured.", 
							content = @Content(mediaType = "application/json"))	
				},
					parameters = {
							@Parameter(
									name = "report_type", 
									allowEmptyValue = false, 
									required = true, 
									description = "The type of report to be created."
									),
							@Parameter(
									name = "trail_id", 
									allowEmptyValue = false, 
									required = true, 
									description = "The trail that is being reported on."
									),
							@Parameter(
									name = "user_id", 
									allowEmptyValue = false, 
									required = true, 
									description = "The id of the author of this report."
									),
							@Parameter(
									name = "report_date", 
									allowEmptyValue = false, 
									required = true, 
									description = "the report date."
									),
							@Parameter(
									name = "location", 
									allowEmptyValue = false, 
									required = false, 
									description = "The location of the report if it is a spot report.  General reports for entire trails should be left null."
									),
							@Parameter(
									name = "description", 
									allowEmptyValue = false, 
									required = false, 
									description = "Update the description of the report."
									)
							
					}
			
			)
	@RequestMapping(value="/create_report",method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	Report createReport(
			//@formatter:off
			@RequestParam(required = true) ReportType report_type,
			@RequestParam(required = true) Long trail_id,
			@RequestParam(required = true) Long user_id,
			@RequestParam(required = true) String report_date,
			@RequestParam(required = false) String description,
			@RequestParam(required = false) String location
			//@formatter:on
	);

}
