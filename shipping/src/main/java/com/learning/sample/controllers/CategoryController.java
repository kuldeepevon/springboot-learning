package com.learning.sample.controllers;

import com.learning.sample.dto.CategoryDto;
import com.learning.sample.interfaces.CategoryService;
import com.learning.sample.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories =  categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") String id ) {
        Category category =  categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> insertCategory( @RequestBody Category category ) {
        Category savedcategory =  categoryService.insertCategory(category);
        return new ResponseEntity<>(savedcategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory( @PathVariable("id") String id, @RequestBody Category category ) {
        Category savedcategory =  categoryService.updateCategory(id, category);
        return new ResponseEntity<>(savedcategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory( @PathVariable("id") String id ) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
