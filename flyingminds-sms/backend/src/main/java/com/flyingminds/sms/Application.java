package com.flyingminds.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Root Spring Boot application for FlyingMinds School Management System.
 * Boots the API server and loads configuration, security, JPA and Flyway.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}