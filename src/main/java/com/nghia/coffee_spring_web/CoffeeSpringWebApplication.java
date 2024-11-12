package com.nghia.coffee_spring_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class CoffeeSpringWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeSpringWebApplication.class, args);
	}

}
