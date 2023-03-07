package com.microservices.product.controllers;

import com.microservices.product.interfaces.CategoryService;
import com.microservices.product.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories =  categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Category> getCategory(@PathVariable("id") String id ) {
        Category category =  categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
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
