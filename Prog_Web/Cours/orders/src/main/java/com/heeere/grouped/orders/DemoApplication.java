package com.heeere.grouped.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/* ___ELASTICSEARCH___ */
@EnableJpaRepositories({"com.heeere.grouped.orders.model.repos", "com.heeere.grouped.orders.model.user"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
}
