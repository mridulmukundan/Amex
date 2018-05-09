package com.springboot.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Boot main class
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class SpringBootEmployeeApplication extends SpringBootServletInitializer{
	
	/* Needed when removing embedded containers
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootEmployeeApplication .class);
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmployeeApplication.class, args);
	}
}
