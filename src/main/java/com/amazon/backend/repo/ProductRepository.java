package com.amazon.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.backend.model.Product;
import com.amazon.backend.model.User;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteById(Long id);
    Optional<Product> findById (Long id);
}
