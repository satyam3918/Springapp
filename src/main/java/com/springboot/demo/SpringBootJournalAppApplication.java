package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class SpringBootJournalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournalAppApplication.class, args);
	}


	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("My Journal App Microservices").description(
				"This microservice handles operations related to Journal Application such as Create Task, Fetch Task, " +
						"Delete Task, Search Task By Name. "));
	}
}
