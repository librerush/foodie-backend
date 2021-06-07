package com.example.foodie.controller;

import com.example.foodie.dto.BrandDto;
import com.example.foodie.dto.ResultDto;
import com.example.foodie.entity.Brand;
import com.example.foodie.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @Operation(summary = "Get all brands")
    List<Brand> getAll() {
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a brand by 'id'")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Brand not found")
    Brand getById(@Parameter(description = "id of brand")
                  @PathVariable Long id) {
        return brandService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a brand")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Error")
    Brand create(@RequestBody BrandDto brandDto) {
        return brandService.create(brandDto);
    }

    @DeleteMapping
    @Operation(summary = "Delete a brand")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Error")
    ResultDto delete(@RequestBody Brand brand) {
        return brandService.delete(brand);
    }
}
