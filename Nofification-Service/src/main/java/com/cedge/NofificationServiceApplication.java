package com.cedge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NofificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NofificationServiceApplication.class, args);
	}

}
