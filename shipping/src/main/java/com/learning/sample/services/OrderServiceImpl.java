package com.learning.sample.services;

import com.learning.sample.interfaces.CategoryService;
import com.learning.sample.interfaces.OrderService;
import com.learning.sample.models.Order;
import com.learning.sample.models.Product;
import com.learning.sample.repository.CategoryRepository;
import com.learning.sample.repository.OrderRepository;
import com.learning.sample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public Order purchaseOrder(Order order) {
//        return null;
        System.out.println("id : " + order.getProduct());
//        Product product = productRepository.findById(order.getProduct().getId()).orElse(null);
//        product.setStock(product.getStock() + order.getQty());
//        productRepository.save(product);
//        order.setType("PURCHASE");
//        order.setCreated(LocalDateTime.now());
//        order.setUpdated(LocalDateTime.now());
//        return orderRepository.save(order);
        return order;

    }

    @Override
    @Transactional
    public Order salesOrder(Order order) {
        Product product = productRepository.findById(order.getProduct().getId()).orElse(null);
        if(product.getStock() < order.getQty()) {
            throw new RuntimeException("Insufficient Quantity");
        }
        product.setStock(product.getStock() - order.getQty());
        productRepository.save(product);
        order.setType("SALES");
        order.setCreated(LocalDateTime.now());
        order.setUpdated(LocalDateTime.now());
        return orderRepository.save(order);
    }
}
