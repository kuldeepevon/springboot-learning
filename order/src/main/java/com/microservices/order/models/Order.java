package com.microservices.order.models;

import com.microservices.order.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    public String id;


    public String sno;

    public String type;
    @DocumentReference(lazy = true)
    public ProductDto product;

    public Integer qty;

    public LocalDateTime created;

    public LocalDateTime updated;
}
