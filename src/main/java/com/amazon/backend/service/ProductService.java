package com.amazon.backend.service;

import org.springframework.stereotype.Service;

import com.amazon.backend.exceptions.ProductNotFoundException;
import com.amazon.backend.exceptions.UserNotFoundException;
import com.amazon.backend.model.Product;
import com.amazon.backend.repo.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getProducts () {
        return repo.findAll();
    }

    public Product getProduct (Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product by id " + id + " was not found."));
    }

    public Product addProduct (Product product) {
        return repo.save(product);
    }

    public Product updateProduct (Long id, Product product) {
        Product oldProduct = getProduct(id);

        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setImage(product.getImage());

        return repo.save(oldProduct);
    }

    public void deleteProduct (Long id) {
        repo.deleteById(id);
    }
}
