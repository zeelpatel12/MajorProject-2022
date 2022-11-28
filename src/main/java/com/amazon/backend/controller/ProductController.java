package com.amazon.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.amazon.backend.dto.ProductDto;
import com.amazon.backend.model.Category;
import com.amazon.backend.service.ApiResponse;
import com.amazon.backend.service.CategoryService;
import com.amazon.backend.service.ProductService;



@RestController
//@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	
	 @Autowired
	    private ProductService productService;
	 @Autowired 
	 	private CategoryService categoryService;
	 @GetMapping("/products")
	    public ResponseEntity<List<ProductDto>> getProducts() {
	        List<ProductDto> body = productService.listProducts();
	        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
	    }

	    @GetMapping("/products/{productID}")
	    public ResponseEntity<ProductDto> ProductById(@PathVariable("productID") Long productID) {
	    	 java.util.Optional<ProductDto> product1 = productService.getProduct(productID);
	    	    ProductDto product = new ProductDto();
	    	    product = product1.get();
	    	    return new ResponseEntity<ProductDto>(product,HttpStatus.OK);

	      }

	    @GetMapping("/productsByCategoryId/{categoryId}")
	    public ResponseEntity<List<ProductDto>> getProductsByKeyWord(@PathVariable("categoryId") Long categoryId) {
	      List<ProductDto> body = productService.listProductsByCategoryId(categoryId);
	      return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
	    }

	    @PostMapping("/products")
	    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
	        java.util.Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
	        if (!((Optional<?>) optionalCategory).isPresent()) {
	            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
	        }
	        Category category = optionalCategory.get();
	        productService.addProduct(productDto, category);
	        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
	    }

	    @PutMapping("/products/{id}")
	    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) {
	        java.util.Optional<Category> optionalCategory = categoryService.readCategory(Long.valueOf(productDto.getCategoryId()));
	        if (!optionalCategory.isPresent()) {
	            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Product is invalid"), HttpStatus.CONFLICT);
	        }
	        Category category = optionalCategory.get();
	        productService.updateProduct(productID, productDto, category);
	        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
	    }

	    @Transactional
	    @DeleteMapping("/products/{id}")
	    public ResponseEntity<?> deleteProduct1 (@PathVariable("id") Long id) {
	        productService.deleteProduct(id);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
}
