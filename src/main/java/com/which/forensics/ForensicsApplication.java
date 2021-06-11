package com.which.forensics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.which"})
@SpringBootApplication
public class ForensicsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ForensicsApplication.class, args);
	}

}
