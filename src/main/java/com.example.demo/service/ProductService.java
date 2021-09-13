package com.example.demo.service;

import com.example.demo.domain.CategoryEntity;
import com.example.demo.domain.ProductEntity;
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

    public ProductEntity createProduct(AddProductDto productDto, CategoryEntity categoryEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDto.getProductName());
        productEntity.setNetto(productDto.getNettoProduct());
        productEntity.setBrutto(productDto.getBruttoProduct());
        productEntity.setCategoryEntity(categoryEntity);
        return saveProduct(productEntity);
    }

    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);

    }
    public Optional<ProductEntity> findByIdProduct(Long id){
        return productRepository.findById(id);
    }

    public Iterable<ProductEntity> findAll(){
        return productRepository.findAll();
    }
}
