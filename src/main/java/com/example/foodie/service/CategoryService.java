package com.example.foodie.service;

import com.example.foodie.dto.ResultDto;
import com.example.foodie.entity.Category;
import com.example.foodie.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ServiceTemplate<Category, Long, String> {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String s) {
        Category category = new Category(s);
        return categoryRepository.save(category);
    }

    @Override
    public ResultDto delete(Category category) {
        try {
            categoryRepository.delete(category);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No category: %s", category));
        }
        return new ResultDto(true, String.format("Deleted: %s", category));
    }

    @Override
    public ResultDto deleteById(Long aLong) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long aLong) {
        return categoryRepository.findById(aLong);
    }

    @Override
    public Category getById(Long aLong) throws ResponseStatusException {
        Optional<Category> category = categoryRepository.findById(aLong);
        if (category.isPresent()) {
            return category.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("No category with id: %d", aLong));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    public boolean existsAny() {
        return categoryRepository.existsAnyCategory();
    }
}
