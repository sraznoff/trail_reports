package com.headwatersgeographic.trail.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.headwatersgeographic.trail.entity.PermittedUse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title="Trails Service"), servers = {@Server(url = "http://localhost:8080", description = "Local Server.")})
public interface PermittedUseController {
	@RequestMapping(value="/permitted_use",method = RequestMethod.GET)
	//@formatter:off
	@Operation(
			summary = "Returns a list of permitted uses",
			description = "Returns a list of permitted uses given optional parameter of trail id.",
			responses={
				@ApiResponse(
						responseCode = "200", 
						description = "A list of permitted uses is returned.", 
						content = @Content(
								mediaType = "application/json", 
								schema = @Schema(implementation = PermittedUse.class))),
				@ApiResponse(
						responseCode = "400", 
						description = "The request parameters are invalid.", 
						content = @Content(mediaType = "application/json")),
				@ApiResponse(
						responseCode = "404", 
						description = "No permitted uses were found with the input criteria.", 
						content = @Content(mediaType = "application/json")),
				@ApiResponse(
						responseCode = "500", 
						description = "An unplanned error occured.", 
						content = @Content(mediaType = "application/json"))	
			},
			parameters = {
				@Parameter(
						name = "trail_id", 
						allowEmptyValue = false, 
						required = false, 
						description = "The Permitted use you want to select by"
						),
			}
	)	
	


	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<PermittedUse> fetchPermittedUse(
			@RequestParam(required = false) Long trail_id
	);
	//@formatter:on
	


}
