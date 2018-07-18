package com.microservices.greenhouseconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GreenhouseConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenhouseConfigServerApplication.class, args);
	}
}
