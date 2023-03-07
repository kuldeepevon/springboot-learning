package com.microservices.product.interfaces;

import com.microservices.product.models.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    public Category getCategory(String id);

    public Category insertCategory(Category category);

    public Category updateCategory(String id, Category category);

    public void deleteCategory(String id);
}
