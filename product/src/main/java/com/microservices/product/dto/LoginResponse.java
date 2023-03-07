package com.microservices.product.dto;

import com.microservices.product.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    String accessToken;

    User userDetails;
}
