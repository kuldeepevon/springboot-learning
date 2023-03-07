package com.learning.sample.controllers;

import com.learning.sample.interfaces.CategoryService;
import com.learning.sample.interfaces.OrderService;
import com.learning.sample.models.Category;
import com.learning.sample.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("purchase")
    public ResponseEntity<Order> purchase(@RequestBody Order order ) {
//        System.out.println("re : " + order.getProduct());
        return new ResponseEntity<>(order, HttpStatus.OK);
//        Order savedorder =  orderService.purchaseOrder(order);
//        return new ResponseEntity<>(savedorder, HttpStatus.OK);
    }

    @PostMapping("sale")
    public ResponseEntity<Order> sale( @RequestBody Order order ) {
        Order savedorder =  orderService.salesOrder(order);
        return new ResponseEntity<>(savedorder, HttpStatus.OK);
    }

}
