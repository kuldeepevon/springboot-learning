package com.microservices.product.services;

import com.microservices.product.interfaces.CategoryService;
import com.microservices.product.models.Category;
import com.microservices.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService  {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category insertCategory(Category category) {
        category.setCreated(LocalDateTime.now());
        category.setUpdated(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(String id, Category category) {
        category.setId(id);
        category.setUpdated(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
