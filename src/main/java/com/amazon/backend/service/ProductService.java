package com.amazon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.exceptions.ProductNotFoundException;
import com.amazon.backend.exceptions.UserNotFoundException;
import com.amazon.backend.model.Category;
import com.amazon.backend.model.Product;
import com.amazon.backend.repo.ProductRepository;


import lombok.AllArgsConstructor;

import com.amazon.backend.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
	@Autowired
private final ProductRepository productRepository ;
	

    public ProductService(ProductRepository productRepository) {
    	super();
        this.productRepository = productRepository;
    }

    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }
    
    public List<ProductDto> listProductsByCategoryId(Long categoryId){
        List<Product> products = productRepository.findByCategoryId(categoryId);
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
          ProductDto productDto = getDtoFromProduct(product);
          productDtos.add(productDto);
        }
        return productDtos;
      }
    
    public List<ProductDto> listProductsByWord(String keyWord){
        List<Product> products = productRepository.searchByTitleLike(keyWord);
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
          ProductDto productDto = getDtoFromProduct(product);
          productDtos.add(productDto);
        }
        return productDtos;
      }
    
    public ProductDto getDtoFromProduct(Product product) {
		ProductDto productDto = new ProductDto(product);
		return productDto;
	}
    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        return product;
    }

    public Optional<ProductDto> getProduct(Long productID) {
        Optional<Product> product = productRepository.findById(productID);
        Optional<ProductDto> productDto = Optional.of(getDtoFromProduct(product.get()));
        return productDto;
    }
	
	 public Product getProductById(Long productID) throws IOException {
	        Optional<Product> optionalProduct = (productRepository.findById(productID));
	        if (!optionalProduct.isPresent())
	            throw new ProductNotFoundException("Product by id " + productID + " was not found.");
	        return optionalProduct.get();
	    }

    public void addProduct (ProductDto productDto, Category category) {
    	Product product =getProductFromDto(productDto, category);
    	productRepository.save(product);
    }

    public void updateProduct(Long productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        product.setId(productDto.getId());
        productRepository.save(product);
    }

    public void deleteProduct (Long id) {
    	productRepository.deleteById(id);
    }
}
