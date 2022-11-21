package com.cybage.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.ecommerce.entities.ProductCategory;
import com.cybage.ecommerce.repositories.ProductCategoryRepo;


@Service
public class ProductCategoryService {

	
	@Autowired
	private ProductCategoryRepo productcategoryrepo;
	
	public ProductCategoryService() {}
	
	public ProductCategory getProductCategoryById(Long id) {
		try {
			ProductCategory productcat = this. productcategoryrepo.findById(id).get();
			return productcat;
		}catch(Exception e) {
			return null;
		}
	}

	public List<ProductCategory> getProducts() {return (List<ProductCategory>) this. productcategoryrepo.findAll();}

	public ProductCategory createProductCategory(ProductCategory productcat) {
		this. productcategoryrepo.save(productcat);
		return productcat;
	}

	public void updateProductCategory(Long pId, ProductCategory pcd) {
		try {
			ProductCategory productcat =  productcategoryrepo.findById(pId).get();
			productcat.setCategory_name(pcd.getCategory_name());
			productcategoryrepo.save(productcat);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteProductCategory(Long id) {
		try {
			ProductCategory productcat = this. productcategoryrepo.findById(id).get();
			this. productcategoryrepo.delete(productcat);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
}
	
}
