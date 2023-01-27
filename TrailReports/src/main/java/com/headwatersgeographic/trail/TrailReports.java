package com.headwatersgeographic.trail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ="com.headwatersgeographic")
public class TrailReports {

	public static void main(String[] args) {
		SpringApplication.run(TrailReports.class, args);

	}

}
