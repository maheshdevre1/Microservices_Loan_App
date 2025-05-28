package com.cedge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class AuthAndAuthorizationServiceApplication {

	public static void main(String[] args) {
		System.out.println("Authentication Service Started");
		SpringApplication.run(AuthAndAuthorizationServiceApplication.class, args);
		System.out.println("Authentication Service Ended");
	}

}
