package com.cybage.ecommerce.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private Long vendorId;
	private String name;
	private Long categoryId;
	private int price;
	private int stock;
	private float gst;
	private String discription;
	private String img;
	
}
