package com.kafka.springboot.app.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	@Id
	private Integer productId;
	private String supplierName;
	private String brandName;
	private String description;
	private String size;
	private String category;
	private String subCategory;
	private Double price;
	private Integer quantity;
	private String country;
	private String sellerCode;
	private String stockStatus;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss", timezone = "Asia/Kolkata" )
	private Timestamp createdDateTime;
	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss",timezone = "Asia/Kolkata")
	private Timestamp updatedDateTime;

}
