package com.microservices.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReqDto {
    public String id;

    public String name;

    public String category;

    public BigDecimal price;

    public Integer stock;

    public String image;

    public LocalDateTime created;

    public LocalDateTime updated;
}
