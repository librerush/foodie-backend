package com.example.foodie.controller;

import com.example.foodie.dto.FileProductDto;
import com.example.foodie.dto.ProductDto;
import com.example.foodie.entity.Brand;
import com.example.foodie.entity.Category;
import com.example.foodie.entity.Product;
import com.example.foodie.service.BrandService;
import com.example.foodie.service.CategoryService;
import com.example.foodie.service.ImageService;
import com.example.foodie.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class FileProductController {
    private final static Logger logger = LoggerFactory.getLogger(FileProductController.class);

    private final CategoryService categoryService;

    private final BrandService brandService;

    private final ImageService imageService;

    private final ProductService productService;

    public FileProductController(CategoryService categoryService, BrandService brandService,
                                 ImageService imageService, ProductService productService) {
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.imageService = imageService;
        this.productService = productService;
    }

    @GetMapping
    @Operation(hidden = true)
    public String get(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        return "product";
    }

    @PostMapping
    @Operation(hidden = true)
    public String upload(@ModelAttribute FileProductDto fileProductDto) {
        List<Category> category = categoryService.findByName(fileProductDto.getCategory());
        List<Brand> brand = brandService.findByName(fileProductDto.getBrand());
        String path = imageService.uploadImage(fileProductDto.getImage());
        ProductDto productDto =
                new ProductDto(fileProductDto.getName(), fileProductDto.getPrice(),
                        category.get(0), brand.get(0), fileProductDto.getDescription(), path);
        logger.info(String.valueOf(productDto));
        Product product = productService.create(productDto);
        logger.info(String.valueOf(product));
        return "/";
    }
}
