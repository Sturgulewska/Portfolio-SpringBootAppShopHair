package com.example.demo.controller;

import com.example.demo.domain.Category;
import com.example.demo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category") // localhost:8080/category
@CrossOrigin // Dodatkowe parametry http np potrzebne do swaggera!!! - poczytaj o TYM !!!!!
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @RequestMapping(
            value = "/category/add_category/{categoryName}",
            method = RequestMethod.POST
    )
    public ResponseEntity<Object> addCategory(@PathVariable("categoryName") String name) {
        Category category = categoryService.createCategory(name);
        return new ResponseEntity<Object>(categoryService.saveCategory(category), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/category/get_category/",
            method = RequestMethod.GET)

    public ResponseEntity<Object> getCategory() {
        Iterable<Category>getCategory = categoryService.findAll();
        return new ResponseEntity<>(getCategory, HttpStatus.OK);
    }

}
