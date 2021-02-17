package com.kafka.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.springboot.app.model.Product;
import com.kafka.springboot.app.service.ProductService;

@RestController
public class ProductController {

	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping(value = "/products",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public String addCustomer(@RequestBody List<Product> products) {
		
		return productService.addProductsToKafkaTopic(products);
	}
	
	
	@GetMapping(value = "/products", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public List<Product> getProducts() {
		
		return productService.findAllProductsFromDB();
	}
	

}
