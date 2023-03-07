package com.microservices.product.services;

import com.microservices.product.interfaces.ProductService;
import com.microservices.product.models.Product;
import com.microservices.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
