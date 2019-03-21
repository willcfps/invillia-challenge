package com.invillia.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EntityScan(value = { "com.invillia.acme.vo" })
@SpringBootApplication(scanBasePackages = { "com.invillia.acme" })
public class InvilliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvilliaApplication.class, args);
	}
}
