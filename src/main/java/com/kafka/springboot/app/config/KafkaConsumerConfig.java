package com.kafka.springboot.app.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.springboot.app.common.KafkaConstants;
import com.kafka.springboot.app.model.Product;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
	@Bean
	public ConsumerFactory<String, Product> consumerFactory(){
		JsonDeserializer<Product> deserializer = new JsonDeserializer<>(Product.class);
	    deserializer.setRemoveTypeHeaders(false);
	    deserializer.addTrustedPackages("com.kafka.springboot.app.model.Product");
	    deserializer.setUseTypeMapperForKey(true);
		
		Map<String, Object> configProps=new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaConstants.BOOTSTRAPSERVERS);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.GROUP_ID);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		
		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), deserializer);
		
	}
	
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Product> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Product> factory=new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
