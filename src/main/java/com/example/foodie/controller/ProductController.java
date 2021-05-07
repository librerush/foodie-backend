package com.example.foodie.controller;

import com.example.foodie.entity.Product;
import com.example.foodie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;


    @GetMapping
    List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/{id}")
    Product getById(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @PostMapping
    Product create(@RequestBody Product product) {
        productRepository.save(product);
        return product;
    }
}
