package com.cedge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentServiceApplication {

	public static void main(String[] args) {
		System.out.println("Payment Service Started");
		SpringApplication.run(PaymentServiceApplication.class, args);
		System.out.println("Payment Service Ended");
	}

}
