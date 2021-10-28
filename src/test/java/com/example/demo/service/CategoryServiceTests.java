package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;


class CategoryServiceTests {

    private CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);

    @Test
    void shouldFindCategoryId() {
        //Given
        CategoryService categoryService = new CategoryService(categoryRepository);
        Category category = new Category();
        category.setId(1L);
        category.setName("2A");


        //When and Then
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));


    }}