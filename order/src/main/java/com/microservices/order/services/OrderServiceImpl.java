package com.microservices.order.services;

import com.microservices.order.dto.OrderDto;
import com.microservices.order.dto.ProductDto;
import com.microservices.order.dto.ProductReqDto;
import com.microservices.order.interfaces.OrderService;
import com.microservices.order.models.Order;
import com.microservices.order.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductProxy productProxy;

    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public Order purchaseOrder(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order, "order");
        System.out.println("id : " + order.getProduct());
        ProductDto product = productProxy.findById(orderDto.getProduct());
        ProductReqDto productReqDto = new ProductReqDto();
        BeanUtils.copyProperties(product, productReqDto, "product");
        productReqDto.setCategory(product.getCategory().getId());
        productReqDto.setStock(product.getStock() + order.getQty());
        productProxy.updateProduct(orderDto.getProduct(), productReqDto);
        order.setType("PURCHASE");
        order.setProduct(product);
        order.setCreated(LocalDateTime.now());
        order.setUpdated(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order salesOrder(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order, "order");
        System.out.println("id : " + order.getProduct());
        ProductDto product = productProxy.findById(orderDto.getProduct());
        if(product.getStock() < orderDto.getQty()) {
            throw new RuntimeException("Insufficient Quantity");
        }
        ProductReqDto productReqDto = new ProductReqDto();
        BeanUtils.copyProperties(product, productReqDto, "product");
        productReqDto.setCategory(product.getCategory().getId());
        productReqDto.setStock(product.getStock() - order.getQty());
        productProxy.updateProduct(orderDto.getProduct(), productReqDto);
        order.setType("SALES");
        order.setProduct(product);
        order.setCreated(LocalDateTime.now());
        order.setUpdated(LocalDateTime.now());
        return orderRepository.save(order);


//        ProductDto product = productProxy.findById(order.getProduct().getId());
//        if(product.getStock() < order.getQty()) {
//            throw new RuntimeException("Insufficient Quantity");
//        }
//        product.setStock(product.getStock() - order.getQty());
//        productProxy.save(product);
//        order.setType("SALES");
//        order.setCreated(LocalDateTime.now());
//        order.setUpdated(LocalDateTime.now());
//        return orderRepository.save(order);
    }
}
