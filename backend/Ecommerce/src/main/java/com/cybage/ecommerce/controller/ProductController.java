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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.ecommerce.entities.Product;
import com.cybage.ecommerce.service.ProductService;



@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable Long id) {
		return service.getProductById(id);
	}

	@GetMapping("/product")
	public List<Product> getProducts() {
		return service.getProducts();
	}
	
	/*
	 * @PostMapping("/product") public Product createEmployee(@RequestBody Product
	 * pd) { return productrepository.save(pd); }
	 */
	
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody Product pd) {
		service.createProduct(pd);
		return new ResponseEntity<>("product created successfully",HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		service.deleteProduct(id);
		return new ResponseEntity<>(id,HttpStatus.OK); 
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody Product pd) {
		service.updateProduct(id, pd);
		return new ResponseEntity<>(pd,HttpStatus.NO_CONTENT); 
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<?> updatePartialyProduct(@PathVariable Long id,@RequestBody Product pd) {
		service.updateProduct(id, pd);
		return new ResponseEntity<>("Product Updated Successfully",HttpStatus.NO_CONTENT); 
	}
}
