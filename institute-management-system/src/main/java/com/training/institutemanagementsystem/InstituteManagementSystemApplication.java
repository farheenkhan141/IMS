package com.training.institutemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class InstituteManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstituteManagementSystemApplication.class, args);
	}

}
