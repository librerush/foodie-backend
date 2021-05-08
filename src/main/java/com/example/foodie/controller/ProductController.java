package com.example.foodie.controller;

import com.example.foodie.entity.Product;
import com.example.foodie.repository.BrandRepository;
import com.example.foodie.repository.CategoryRepository;
import com.example.foodie.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @Operation(summary = "Get all products")
    List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by 'id'")
    Optional<Product> getById(@Parameter(description = "id of product")
                              @PathVariable Long id) {
        return productRepository.findById(id);
    }

    @PostMapping
    @Operation(summary = "Please, ignore 'order' field when sending JSON")
    Product create(@RequestBody Product product) {
        brandRepository.save(product.getBrand());
        categoryRepository.save(product.getCategory());
        productRepository.save(product);
        return product;
    }
}
