package com.nghia.coffee_spring_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghia.coffee_spring_web.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();

    Product save(Product product);

}
