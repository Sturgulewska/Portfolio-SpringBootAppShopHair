package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class ProductServiceTests {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    @Test
    void shouldFindCategoryId() {
        //Given
        Category category = new Category();
        category.setId(1L);
        category.setName("2A");


        ProductService productService = new ProductService(productRepository);
        Product product = new Product();
        product.setId(1L);
        product.setName("Odżywka proteinowa Anwen");
        product.setCategory(category);
        product.setNetto(25.6F);
        product.setBrutto(30.7F);

        //When and Then
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    }
}