package com.example.foodie.controller;

import com.example.foodie.entity.Brand;
import com.example.foodie.entity.Category;
import com.example.foodie.entity.Product;
import com.example.foodie.repository.BrandRepository;
import com.example.foodie.repository.CategoryRepository;
import com.example.foodie.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Operation(summary = "Get a product by 'id'")
    Optional<Product> getById(@Parameter(description = "id of product")
                              @PathVariable Long id) {
        return productRepository.findById(id);
    }

    @GetMapping("/search/{name}")
    @Operation(summary = "Get the products by name")
    List<Product> getByName(@Parameter(description = "product name")
                            @PathVariable String name) {
        return productRepository.findProductByName(name);
    }

    @GetMapping("/category/{name}")
    @Operation(summary = "Get the products by category")
    List<Product> getByCategory(@Parameter(description = "category name")
                                @PathVariable String name) {
        List<Category> categories = categoryRepository.findCategoryByName(name);
        if (categories == null || categories.size() < 1) {
            return new ArrayList<>();
        }
        return productRepository.findProductByCategory(categories.get(0));
    }

    @GetMapping("/brand/{name}")
    @Operation(summary = "Get the products by brand")
    List<Product> getByBrand(@Parameter(description = "brand name")
                             @PathVariable String name) {
        List<Brand> brands = brandRepository.findBrandByName(name);
        if (brands == null || brands.size() < 1) {
            return new ArrayList<>();
        }
        return productRepository.findProductByBrand(brands.get(0));
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
