package com.learning.sample.repository;

import com.learning.sample.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends MongoRepository<Category, String> {
}
