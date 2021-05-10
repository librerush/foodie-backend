package com.example.foodie.repository;

import com.example.foodie.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>,
        org.springframework.data.repository.Repository<Category, Long> {

    boolean existsByNameLike(String name);

    List<Category> findCategoryByName(String name);
}
