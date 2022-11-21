package com.cybage.ecommerce.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product_category")
public class ProductCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long category_id;
	
	@Column(name = "category_name")
	private String category_name;


	 public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(Long category_id, String category_name, List<Product> products) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		/* this.products = products; */
	}

	@Override
	public String toString() {
		return "ProductCategory [category_id=" + category_id + ", category_name=" + category_name + "]";
	}

	/*
	 * , products=" + products + "
	 */
	
}
