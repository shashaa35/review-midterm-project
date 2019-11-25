package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.ReviewMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewMongoRepository extends MongoRepository<ReviewMongo, Integer> {
}
