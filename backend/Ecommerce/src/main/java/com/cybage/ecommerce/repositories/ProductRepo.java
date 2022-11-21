package com.cybage.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.ecommerce.entities.Product;


public interface ProductRepo extends JpaRepository<Product, Long > {

}
