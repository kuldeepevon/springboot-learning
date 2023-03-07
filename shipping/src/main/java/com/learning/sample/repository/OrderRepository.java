package com.learning.sample.repository;

import com.learning.sample.models.Category;
import com.learning.sample.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>  {
}
