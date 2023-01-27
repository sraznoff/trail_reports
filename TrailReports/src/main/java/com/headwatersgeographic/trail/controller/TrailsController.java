package com.headwatersgeographic.trail.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.headwatersgeographic.trail.entity.Trail;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title="Trails Service"), servers = {@Server(url = "http://localhost:8080", description = "Local Server.")})
public interface TrailsController {
	@RequestMapping(value="/trails",method = RequestMethod.GET)
	//@formatter:off
	@Operation(
			summary = "Returns a list of trails",
			description = "Returns a list of trails given optional parameters of difficulty and use.",
			responses={
				@ApiResponse(
						responseCode = "200", 
						description = "A list of trails is returned.", 
						content = @Content(
								mediaType = "application/json", 
								schema = @Schema(implementation = Trail.class))),
				@ApiResponse(
						responseCode = "400", 
						description = "The request parameters are invalid.", 
						content = @Content(mediaType = "application/json")),
				@ApiResponse(
						responseCode = "404", 
						description = "No trails were found with the input criteria.", 
						content = @Content(mediaType = "application/json")),
				@ApiResponse(
						responseCode = "500", 
						description = "An unplanned error occured.", 
						content = @Content(mediaType = "application/json"))	
			},
			parameters = {
				@Parameter(
						name = "permittedUse", 
						allowEmptyValue = false, 
						required = false, 
						description = "The Permitted use you want to select by"
						),
				/*@Parameter(
						name = "trim",
						allowEmptyValue = false,
						required = false, 
						description = "The trim level (e.g. 'sport')"
						)*/
			}
	)	
	

//	@GetMapping
//	@ResponseStatus(code = HttpStatus.OK)
//	List<Trail> fetchTrails(
//			//@RequestParam(required = false) JeepModel model, 
//			//@RequestParam(required = false)String trim
//	);

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<Trail> fetchTrailsByUse(
			@RequestParam(required = false) String permittedUse
			//@RequestParam(required = false)String trim
	);
	//@formatter:on
	


}
