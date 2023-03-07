package com.microservices.product.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    public String id;

    public String email;

    public String password;

    public String firstName;

    public String lastName;

    public List<String> roles;
}
