package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.domain.Product;
import com.example.demo.domain.dto.AddProductDto;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(AddProductDto productDto, Category category) {
        Product productEntity = new Product();
        productEntity.setName(productDto.getProductName());
        productEntity.setNetto(productDto.getNettoProduct());
        productEntity.setBrutto(productDto.getBruttoProduct());
        productEntity.setCategory(category);
        return saveProduct(productEntity);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);

    }
    public Optional<Product> findByIdProduct(Long id){
        return productRepository.findById(id);
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }
}
