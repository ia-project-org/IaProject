package com.ai.apiLayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ai")
@EntityScan(basePackages = "com.ai.dataLayer.models")
@EnableJpaRepositories(basePackages = "com.ai.dataLayer.repositories")
@ConfigurationPropertiesScan("com.ai")
public class ApiLayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLayerApplication.class, args);
	}

}
