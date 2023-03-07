package com.microservices.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    @Id
    public String id;

    public String name;

    public LocalDateTime created;

    public LocalDateTime updated;
}
