package com.example.foodie.service;

import com.example.foodie.dto.ProductDto;
import com.example.foodie.dto.ResultDto;
import com.example.foodie.entity.Brand;
import com.example.foodie.entity.Category;
import com.example.foodie.entity.Product;
import com.example.foodie.repository.BrandRepository;
import com.example.foodie.repository.CategoryRepository;
import com.example.foodie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ServiceTemplate<Product, Long, ProductDto>  {
    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product create(ProductDto productDto) {
        brandRepository.save(productDto.getBrand());
        categoryRepository.save(productDto.getCategory());
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        return productRepository.save(product);
    }

    @Override
    public ResultDto delete(Product product) {
        try {
            productRepository.delete(product);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + product);
        }
        return new ResultDto(true, "Deleted product with id: " + product);
    }

    @Override
    public ResultDto deleteById(Long aLong) {
        try {
            productRepository.deleteById(aLong);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id not found: " + aLong);
        }
        return new ResultDto(true, "Deleted product with id: " + aLong);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }

    @Override
    public Product getById(Long id) throws ResponseStatusException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product with id: " + id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> getByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Transactional
    public List<Product> getByCategory(String name) {
        List<Category> categories = categoryRepository.findCategoryByName(name);
        if (categories == null || categories.size() < 1) {
            return new ArrayList<>();
        }
        return productRepository.findProductByCategory(categories.get(0));
    }

    @Transactional
    public List<Product> getByBrand(String name) {
        List<Brand> brands = brandRepository.findBrandByName(name);
        if (brands == null || brands.size() < 1) {
            return new ArrayList<>();
        }
        return productRepository.findProductByBrand(brands.get(0));
    }
}
