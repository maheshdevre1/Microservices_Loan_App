package com.cedge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CardServiceApplication {

	public static void main(String[] args) {
		System.out.println("Card Service started");
		SpringApplication.run(CardServiceApplication.class, args);
		System.out.println("Card Service ended");
	}

}
