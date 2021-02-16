package com.kafka.springboot.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.springboot.app.common.KafkaConstants;
import com.kafka.springboot.app.model.Product;
import com.kafka.springboot.app.repository.ProductRepository;

@Service
public class ProductService {

	static Logger logger = LoggerFactory.getLogger(ProductService.class.getName());

	private KafkaTemplate<String, Product> kafkaTemplate;

	private ProductRepository productRepository;

	@Autowired
	public void setKafkaTemplate(KafkaTemplate<String, Product> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Autowired
	public void setProductsRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public String addProductsToTopic(List<Product> products) {

		try {
			if (!products.isEmpty()) {
				for (Product product : products) {
					try {
						kafkaTemplate.send(KafkaConstants.TOPIC, product);
						logger.info("***********Message Published to Kafka topic************");
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Customer Record added to Kafka Queue successfully";
	}

	@KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)
	public Product listener(Product product) {
		logger.info("***Message received from kafka topic:: " + product);
		productRepository.save(product);
		return product;
	}
	
	public void checkIfProductIsAlreadyExisting(List<Product> products) {
		
		
		products.forEach(product->{
			
			Optional<Product> findById = productRepository.findById(product.getProductId());
			
			if(findById.isPresent()) {
				Product p = findById.get();
				product.setCreatedDateTime(p.getCreatedDateTime());
				product.setUpdatedDateTime(new Timestamp(new Date().getTime()));
			}else {
				product.setCreatedDateTime(new Timestamp(new Date().getTime()));
				product.setUpdatedDateTime(new Timestamp(new Date().getTime()));
			}
		});
		
		
		
		
	}

}
