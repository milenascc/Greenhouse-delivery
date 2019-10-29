package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class OrderCompleteUpdaterApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderCompleteUpdaterApp.class, args);
    }
}
