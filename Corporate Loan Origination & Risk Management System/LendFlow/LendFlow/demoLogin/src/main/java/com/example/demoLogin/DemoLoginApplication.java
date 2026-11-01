package com.example.demoLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demoLogin.controller","com.example.demoLogin.dao", "com.example.demoLogin.service"})
public class DemoLoginApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoLoginApplication.class, args);
	}
}
