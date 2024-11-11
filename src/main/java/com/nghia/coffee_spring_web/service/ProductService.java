package com.nghia.coffee_spring_web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nghia.coffee_spring_web.domain.Product;
import com.nghia.coffee_spring_web.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProduct() {
        return this.productRepository.findAll();
    }

    public Optional<Product> fetchProductById(long id) {
        return this.productRepository.findById(id);
    }

    public Product handleSaveAProduct(Product product) {
        return this.productRepository.save(product);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

}
