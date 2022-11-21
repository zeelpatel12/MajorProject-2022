package com.cybage.ecommerce.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "vendor_id")
	private Long vendorId;

	@Column(name = "product_name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "stock")
	private int stock;

	@Column(name = "gst")
	private float gst;

	@Column(name = "discription")
	private String discription;

	@Column(name = "image_url")
	private String img;

	@CreationTimestamp
	@Column(name = "creation_date")
	private Timestamp creationDate;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private Timestamp updatedDate;

	
	
	  @ManyToOne(cascade = CascadeType.ALL)
	 
	  @JoinColumn(name="category_id") 
	  private ProductCategory category_id;
	  public ProductCategory getCategory_Id() { return category_id; }
	  
	  public void setCategory_Id(ProductCategory category_id) { this.category_id =
	  category_id; }
	 

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getGst() {
		return gst;
	}

	public void setGst(float gst) {
		this.gst = gst;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Product() {
		super();

	}

	public Product(Long id, Long vendorId, String name, Long categoryId, BigDecimal price, int stock, float gst,
			String discription, String img, Timestamp creationDate, Timestamp updatedDate,
			ProductCategory category_Id) {
		super();
		Id = id;
		this.vendorId = vendorId;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.gst = gst;
		this.discription = discription;
		this.img = img;
		this.creationDate = creationDate;
		this.updatedDate = updatedDate;
		 this.category_id = category_Id; 
	}

	@Override
	public String toString() {
		return "Product [Id=" + Id + ", vendorId=" + vendorId + ", name=" + name + ", price=" + price + ", stock="
				+ stock + ", gst=" + gst + ", discription=" + discription + ", img=" + img + ", creationDate="
				+ creationDate + ", updatedDate=" + updatedDate + " ,category_Id=\\\" + category_Id + \\\"]";
	}

	 
}
