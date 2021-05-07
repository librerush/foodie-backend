package com.example.foodie.repository;

import com.example.foodie.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>,
        org.springframework.data.repository.Repository<Brand, Long> {
    
    List<Brand> findBrandByName(String name);
}
