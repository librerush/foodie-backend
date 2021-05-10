package com.example.foodie.controller;

import com.example.foodie.entity.Category;
import com.example.foodie.repository.CategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @Operation(summary = "Get all categories")
    List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by 'id'")
    Category getById(@Parameter(description = "id of category")
                     @PathVariable Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No category with id: " + id);
    }
}
