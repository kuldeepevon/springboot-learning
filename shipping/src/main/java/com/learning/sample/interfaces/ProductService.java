package com.learning.sample.interfaces;

import com.learning.sample.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProduct(String id);

    public Product insertProduct(Product product);

    public Product updateProduct(String id, Product product);

    public void deleteProduct(String id);
}
