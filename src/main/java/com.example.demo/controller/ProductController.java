package com.example.demo.controller;

import com.example.demo.domain.CategoryEntity;
import com.example.demo.domain.ProductEntity;
import com.example.demo.domain.dto.AddProductDto;
import com.example.demo.domain.dto.ErrorDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequestMapping("/product") //localhost:8080/product
@CrossOrigin
@RestController
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/product/get_products_by_category/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductsByCategory(@PathVariable("id") Long id) {
        Optional<ProductEntity> optionalProduct = productService.findByIdProduct(id);
        return new ResponseEntity<>(optionalProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/get_all_products" ,method = RequestMethod.GET)
    public ResponseEntity<Object> getAllProducts(){
        Iterable<ProductEntity> getAllProduct = productService.findAll();
    return new ResponseEntity<>(getAllProduct, HttpStatus.OK);}

    @RequestMapping(value = "/products/add_product", method = RequestMethod.POST)
    public ResponseEntity<Object> addProduct(@RequestBody @Valid AddProductDto addProductDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorDto> errorDtoList = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(e -> errorDtoList.add(new ErrorDto(e.getDefaultMessage(), e.getField())));
            return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
        }

        Optional<CategoryEntity> optionalCategory = categoryService.findByIdCategory(addProductDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<>(new ErrorDto("Nie znaleziono kategorii", "category_id "), HttpStatus.BAD_REQUEST);
        }

        productService.createProduct( addProductDto, optionalCategory.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
