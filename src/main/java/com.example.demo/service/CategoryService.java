package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<Category> getAllCategory(){
        return (List<Category>) categoryRepository.findAll();
    }
    public Optional<Category> findByIdCategory(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return saveCategory(category);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
