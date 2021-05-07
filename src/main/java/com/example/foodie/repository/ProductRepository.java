package com.example.foodie.repository;

import com.example.foodie.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long>,
        org.springframework.data.repository.Repository<Product, Long> {

    List<Product> findProductByName(String name);
}
