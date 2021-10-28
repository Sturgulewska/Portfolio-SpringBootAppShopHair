package com.example.demo.controller;

import com.example.demo.domain.Category;
import com.example.demo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
@CrossOrigin(origins = {"http://localhost:8080"})
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
        String hallo = " Hallo World";
      //  Iterable<Category> getCategory = categoryService.getAllCategory();
        return new ResponseEntity<>(hallo, HttpStatus.OK);
    }

}
