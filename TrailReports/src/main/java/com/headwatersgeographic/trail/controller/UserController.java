package com.headwatersgeographic.trail.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.headwatersgeographic.trail.entity.User;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title="User Service"), servers = {@Server(url = "http://localhost:8080", description = "Local Server.")})
public interface UserController {
	//@formatter:off
	@RequestMapping(value = "/users", method=RequestMethod.GET)
	@Operation(
			summary = "Returns a list of users",
			description = "Returns a list of users",
			responses = {
				@ApiResponse(
						responseCode = "200",
						description = "A list of users is returned.",
						content = @Content(
								mediaType = "application/json",
								schema = @Schema(implementation = User.class)
								)
				),
				@ApiResponse(
						responseCode = "400",
						description = "The request parameters are invalid.",
						content = @Content(mediaType = "application/json")
						),
				@ApiResponse(
						responseCode = "404",
						description = "No users were found with the input criteria.",
						content = @Content(mediaType = "application/json")
						),
				@ApiResponse(
						responseCode = "500", 
						description = "An unplanned error occured.", 
						content = @Content(mediaType = "application/json")
						)					
			}	
			
		)
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<User> fetchUsers();
	

}
