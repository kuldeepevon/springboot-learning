package com.microservices.order.interfaces;

import com.microservices.order.dto.OrderDto;
import com.microservices.order.models.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public Order purchaseOrder(OrderDto orderDto);

    public Order salesOrder(OrderDto orderDto);
}
