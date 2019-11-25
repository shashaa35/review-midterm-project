package com.udacity.course3.reviews.RepositoryTests;

import com.udacity.course3.reviews.entity.ReviewMongo;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RopsitoryMongoTests {
    private static ReviewMongo reviewMongo;
    @Autowired
    ReviewMongoRepository reviewMongoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void test() {
        reviewMongo = new ReviewMongo(1,"This is review", 1);
        reviewMongo = mongoTemplate.save(reviewMongo,"reviews");
        Assert.assertEquals(reviewMongoRepository.findById(1).get().getReview(),"This is review");
    }
}
