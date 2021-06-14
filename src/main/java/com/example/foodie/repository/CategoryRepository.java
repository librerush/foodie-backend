package com.example.foodie.repository;

import com.example.foodie.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameLike(String name);

    @Query("select case when count(c) > 0 then true else false end from Category c")
    boolean existsAnyCategory();

    List<Category> findCategoryByName(String name);
}
