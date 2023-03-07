package com.microservices.order.controllers;

import com.microservices.order.dto.OrderDto;
import com.microservices.order.interfaces.OrderService;
import com.microservices.order.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("")
    public ResponseEntity<String> defaultRoute( ) {
        return new ResponseEntity<>("Default Order", HttpStatus.OK);
    }
    @GetMapping("first")
    public ResponseEntity<String> firstRoute( ) {
        return new ResponseEntity<>("First Order", HttpStatus.OK);
    }
    @PostMapping("purchase")
    public ResponseEntity<Order> purchase(@RequestBody OrderDto orderDto ) {
//        System.out.println("re : " + order.getProduct());
//        Order order = new Order();
//        order.setSno(orderDto.getSno());
//        order.setProduct(orderDto.getProduct());
//        order.setQty(orderDto.getQty());
//        return new ResponseEntity<>(orderDto, HttpStatus.OK);
        Order savedorder =  orderService.purchaseOrder(orderDto);
        return new ResponseEntity<>(savedorder, HttpStatus.OK);
    }

    @PostMapping("sale")
    public ResponseEntity<Order> sale( @RequestBody OrderDto orderDto ) {
        Order savedorder =  orderService.salesOrder(orderDto);
        return new ResponseEntity<>(savedorder, HttpStatus.OK);
    }

}
