package com.microservices.product.repository;

import com.microservices.product.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends MongoRepository<Category, String> {
}
