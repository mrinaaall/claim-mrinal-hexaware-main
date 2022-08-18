package com.claimManagement.claim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ClaimApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimApplication.class, args);
	}

}
