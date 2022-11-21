package com.cybage.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.ecommerce.entities.ProductCategory;
import com.cybage.ecommerce.service.ProductCategoryService;



@RestController
@CrossOrigin
public class ProductCategoryController {

	@Autowired
	ProductCategoryService categoryservice;
	
	@GetMapping("/productcategory/{id}")
	public ProductCategory getProductCategory(@PathVariable Long id) {
		return categoryservice.getProductCategoryById(id);
	}

	@GetMapping("/productcategory")
	public List<ProductCategory> getProducts() {
		return categoryservice.getProducts();
	}
	
	@PostMapping("/createproctcategory")
	public ResponseEntity<?> createProductCategory(@RequestBody ProductCategory pcd) {
		categoryservice.createProductCategory(pcd);
		return new ResponseEntity<>(pcd,HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/deleteproductcategory/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		categoryservice.deleteProductCategory(id);
		return new ResponseEntity<>(id,HttpStatus.OK); 
	}
	
	@PutMapping("/updateproductcategory/{id}")
	public ResponseEntity<?> updateProductCategory(@PathVariable Long id,@RequestBody ProductCategory pcd) {
		categoryservice.updateProductCategory(id, pcd);
		return new ResponseEntity<>(pcd,HttpStatus.NO_CONTENT); 
	}
	
	@PatchMapping("/updateproductcategory/{id}")
	public ResponseEntity<?> updatePartialyProductCategory(@PathVariable Long id,@RequestBody ProductCategory pcd) {
		categoryservice.updateProductCategory(id, pcd);
		return new ResponseEntity<>("Product Updated Successfully",HttpStatus.NO_CONTENT); 
	}
}
