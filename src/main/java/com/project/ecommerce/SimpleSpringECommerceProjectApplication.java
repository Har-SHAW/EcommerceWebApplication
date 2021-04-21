package com.project.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SimpleSpringECommerceProjectApplication {
	public static final Logger logger = LoggerFactory.getLogger(SimpleSpringECommerceProjectApplication.class);
	public static void main(String[] args) {
		logger.info("started");
		SpringApplication.run(SimpleSpringECommerceProjectApplication.class, args);
	}
}
