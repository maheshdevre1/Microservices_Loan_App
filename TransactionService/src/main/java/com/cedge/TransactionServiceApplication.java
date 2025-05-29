package com.cedge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TransactionServiceApplication {

	public static void main(String[] args) {
		System.out.println("Transaction Service Started ...!");
		SpringApplication.run(TransactionServiceApplication.class, args);
		System.out.println("Transaction Service Ended ...!");
	}

}
