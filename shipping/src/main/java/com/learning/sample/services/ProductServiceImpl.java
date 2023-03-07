package com.learning.sample.services;

import com.learning.sample.interfaces.ProductService;
import com.learning.sample.models.Product;
import com.learning.sample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product insertProduct(Product product) {
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String id, Product product) {
        product.setId(id);
        product.setUpdated(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
