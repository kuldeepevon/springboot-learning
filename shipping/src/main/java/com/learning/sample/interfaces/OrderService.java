package com.learning.sample.interfaces;

import com.learning.sample.models.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public Order purchaseOrder(Order order);

    public Order salesOrder(Order order);
}
