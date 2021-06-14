package com.example.foodie.repository;

import com.example.foodie.entity.Brand;
import com.example.foodie.entity.Category;
import com.example.foodie.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(?1) , '%')")
    Stream<Product> findProductByName(String name);

    @Query("select p from Product p")
    Stream<Product> findAllAsStream();

    List<Product> findProductByBrand(Brand brand);

    List<Product> findProductByCategory(Category category);

    @Query("select case when count(p) > 0 then true else false end from Product p")
    boolean existsAnyProduct();
}
