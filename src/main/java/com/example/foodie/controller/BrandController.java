package com.example.foodie.controller;

import com.example.foodie.entity.Brand;
import com.example.foodie.repository.BrandRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping
    @Operation(summary = "Get all brands")
    List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a brand by 'id'")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Brand not found")
    Brand getById(@Parameter(description = "id of brand")
                  @PathVariable Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            return brandOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No brand with id: " + id);
    }
}
