package com.amazon.backend.dto;

import com.amazon.backend.model.Product;

public class ProductDto {	
	private Long id;
    private  String name;
    private  byte[] imageURL;
    private  double price;
    private int stock;
    private  String description;
    private  Long categoryId;
    
    public ProductDto() {}
    
    
	public ProductDto(String name, byte[] imageURL, double price, int stock, String description, Long categoryId) {
		super();
		this.name = name;
		this.imageURL = imageURL;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.categoryId = categoryId;
	}

	public ProductDto(Product product) {
		this.setId(product.getId());
		this.setName(product.getName());
		this.setImageURL(product.getImage());
		this.setDescription(product.getDescription());
		this.setPrice(product.getPrice());
		this.setStock(product.getStock());
		this.setCategoryId(product.getCategory().getId());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImageURL() {
		return imageURL;
	}
	public void setImageURL(byte[] bs) {
		this.imageURL = bs;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
    
    

}
