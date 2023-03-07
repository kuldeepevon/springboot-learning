package com.microservices.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    public String id;

    public String sno;

    public String type;
    public String product;

    public Integer qty;

    public LocalDateTime created;

    public LocalDateTime updated;
}
