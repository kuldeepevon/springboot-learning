package com.learning.sample.repository;

import com.learning.sample.models.Category;
import com.learning.sample.models.CustomMsg;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomMsgRepository extends MongoRepository<CustomMsg, String> {
}
