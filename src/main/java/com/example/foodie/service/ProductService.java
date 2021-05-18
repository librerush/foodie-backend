package com.example.foodie.service;

import com.example.foodie.entity.Product;
import com.example.foodie.repository.BrandRepository;
import com.example.foodie.repository.CategoryRepository;
import com.example.foodie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Modifying
    public Product create(Product product) {
        brandRepository.save(product.getBrand());
        categoryRepository.save(product.getCategory());
        productRepository.save(product);
        return product;
    }
}
