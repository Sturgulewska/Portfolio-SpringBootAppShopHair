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
        Product product = new Product();
        product.setName(productDto.getProductName());
        product.setNetto(productDto.getNettoProduct());
        product.setBrutto(productDto.getBruttoProduct());
        product.setCategory(category);
        return saveProduct(product);
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
