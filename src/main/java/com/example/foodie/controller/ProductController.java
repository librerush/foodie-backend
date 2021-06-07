package com.example.foodie.controller;

import com.example.foodie.dto.ProductDto;
import com.example.foodie.dto.ResultDto;
import com.example.foodie.entity.Product;
import com.example.foodie.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Get all products")
    List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by 'id'")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Product not found")
    Product getById(@Parameter(description = "id of product")
                    @PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/search/{name}")
    @Operation(summary = "Get the products by name")
    List<Product> getByName(@Parameter(description = "product name")
                            @PathVariable String name) {
        return productService.getByName(name);
    }

    @GetMapping("/category/{name}")
    @Operation(summary = "Get the products by category")
    List<Product> getByCategory(@Parameter(description = "category name")
                                @PathVariable String name) {
        return productService.getByCategory(name);
    }

    @GetMapping("/brand/{name}")
    @Operation(summary = "Get the products by brand")
    List<Product> getByBrand(@Parameter(description = "brand name")
                             @PathVariable String name) {
        return productService.getByBrand(name);
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    Product create(@RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }

    @PutMapping
    @Operation(summary = "Update a product")
    Product update(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    ResultDto delete(@Parameter(description = "id of product")
                     @PathVariable Long id) {
        return productService.deleteById(id);
    }
}
