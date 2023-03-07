package com.microservices.product.repository;

import com.microservices.product.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmail(String email);
}
