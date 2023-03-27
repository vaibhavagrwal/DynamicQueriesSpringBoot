package com.sub.sublayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = "com.sub.sublayer.*")
//@EntityScan("com.sub.sublayer.*")
//@EnableJpaRepositories(basePackages = "com.sub.sublayer.repository")
public class SublayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SublayerApplication.class, args);
	}

}
