package com.example.foodie.controller;

import com.example.foodie.service.LoadProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/load/product")
public class LoadProductController {
    private final LoadProductService loadProductService;

    public LoadProductController(LoadProductService loadProductService) {
        this.loadProductService = loadProductService;
    }

    @GetMapping
    @Operation(hidden = true)
    public String load() {
        String staticPath = "static";
        try {
            loadProductService.saveCategories(String
                    .format("%s/categories.csv", staticPath));
            loadProductService.saveBrand(String
                    .format("%s/brands.csv", staticPath));
            loadProductService.saveProduct(String
                    .format("%s/data.csv", staticPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/swagger-ui.html";
    }
}
