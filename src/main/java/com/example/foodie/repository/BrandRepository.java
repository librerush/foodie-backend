package com.example.foodie.repository;

import com.example.foodie.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
    List<Brand> findBrandByName(String name);

    @Query("select case when count(b) > 0 then true else false end from Brand b")
    boolean existsAnyBrand();
}
