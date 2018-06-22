package com.microservices.delivery1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com.microservices.delivery1.load"})
@EntityScan({"com.microservices.delivery1"})
@EnableJpaRepositories({"com.microservices.delivery1"})
public class Delivery1Application {

    public static void main(String[] args) { SpringApplication.run(Delivery1Application.class, args);
}
}
