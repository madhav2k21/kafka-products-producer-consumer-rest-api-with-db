package com.kafka.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.springboot.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
