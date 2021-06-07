package com.example.foodie.controller;

import com.example.foodie.dto.ResultDto;
import com.example.foodie.entity.Category;
import com.example.foodie.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category by 'id'")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Category not found")
    Category getById(@Parameter(description = "id of category")
                     @PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a category")
    Category create(@Parameter(description = "name of category")
                    @RequestParam("name") String categoryName) {
        return categoryService.create(categoryName);
    }

    @DeleteMapping
    @Operation(summary = "Delete a category")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Error")
    ResultDto delete(@RequestBody Category category) {
        return categoryService.delete(category);
    }
}
