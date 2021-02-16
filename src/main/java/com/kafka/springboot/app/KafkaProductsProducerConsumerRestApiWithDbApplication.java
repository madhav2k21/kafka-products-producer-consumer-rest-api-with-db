package com.kafka.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.kafka.springboot.app.repository.ProductRepository;
@EnableJpaRepositories(basePackageClasses = ProductRepository.class)
@SpringBootApplication
public class KafkaProductsProducerConsumerRestApiWithDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProductsProducerConsumerRestApiWithDbApplication.class, args);
	}

}
