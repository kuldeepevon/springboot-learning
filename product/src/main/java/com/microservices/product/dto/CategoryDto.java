package com.microservices.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    public String id;

    public String name;

    public LocalDateTime created;

    public LocalDateTime updated;
}
