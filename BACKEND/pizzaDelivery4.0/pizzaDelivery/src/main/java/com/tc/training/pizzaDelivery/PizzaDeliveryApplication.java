package com.tc.training.pizzaDelivery;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaDeliveryApplication {
	static Logger logger = LoggerFactory.getLogger(PizzaDeliveryApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PizzaDeliveryApplication.class, args);
		logger.info("App is starting");
	}


}
