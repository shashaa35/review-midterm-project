package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.ReviewMongo;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewMongoRepository reviewMongoRepository;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId, @RequestBody Review review) {
        System.out.println("Creating review");
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            System.out.println("product found");
            review.setProduct(product.get());
            reviewRepository.save(review);
            return ResponseEntity.ok(reviewMongoRepository.save(new ReviewMongo(review.getId(),review.getReview(),0)));
        }
        else {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewMongo>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        if(productRepository.existsById(productId)){
            List<Review> reviews = reviewRepository.findAllReviewsByProduct(productRepository.findById(productId).get());
            List<Integer> reviewIds = new ArrayList<>();
            for( Review rev : reviews){
                reviewIds.add(rev.getId());
            }
            return ResponseEntity.ok((List<ReviewMongo>)reviewMongoRepository.findAllById(reviewIds));
        }
        else{
            throw new HttpServerErrorException((HttpStatus.NOT_FOUND));
        }


    }
}