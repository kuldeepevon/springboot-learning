package com.learning.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    public String id;

    public String name;

    public String category;

    public BigDecimal price;

    public Integer stock;

    public LocalDateTime created;

    public LocalDateTime updated;
}
