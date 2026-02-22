package com.tasto.backend.controller;

import com.tasto.backend.dto.CategoryResponse;
import com.tasto.backend.entity.CategoryModel;
import com.tasto.backend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService=categoryService;
    }
    @PostMapping("/add-category")
    ResponseEntity<CategoryResponse> categoryAdd(@RequestBody List<CategoryModel> categoryRequest)
    {
        CategoryResponse response = categoryService.addCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
