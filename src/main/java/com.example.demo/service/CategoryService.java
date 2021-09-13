package com.example.demo.service;

import com.example.demo.domain.CategoryEntity;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<CategoryEntity> findAll(){
        return categoryRepository.findAll();
    }
    public Optional<CategoryEntity> findByIdCategory(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryEntity createCategory(String name) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        return saveCategory(categoryEntity);
    }

    public CategoryEntity saveCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }
}
