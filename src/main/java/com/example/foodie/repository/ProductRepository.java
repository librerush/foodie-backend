package com.example.foodie.repository;

import com.example.foodie.entity.Brand;
import com.example.foodie.entity.Category;
import com.example.foodie.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(?1) , '%')")
    List<Product> findProductByName(String name);

    List<Product> findProductByBrand(Brand brand);

    List<Product> findProductByCategory(Category category);
}
