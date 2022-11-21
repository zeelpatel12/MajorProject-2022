package com.cybage.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.ecommerce.entities.Product;
import com.cybage.ecommerce.repositories.ProductRepo;


@Service
public class ProductService {

	
	@Autowired
	private ProductRepo productrepo;
	
	public ProductService() {}
	
	public Product getProductById(Long id) {
		try {
			Product product = this.productrepo.findById(id).get();
			return product;
		}catch(Exception e) {
			return null;
		}
	}

	public List<Product> getProducts() {return (List<Product>) this.productrepo.findAll();}

	public Product createProduct(Product product) {
		this.productrepo.save(product);
		return product;
	}

	public void updateProduct(Long pId, Product pd) {
		try {
			Product product = productrepo.findById(pId).get();
			product.setName(pd.getName());
			product.setDiscription(pd.getDiscription());
			product.setPrice(pd.getPrice());
			
			productrepo.save(product);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteProduct(Long id) {
		try {
			Product product = this.productrepo.findById(id).get();
			this.productrepo.delete(product);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
}
}
