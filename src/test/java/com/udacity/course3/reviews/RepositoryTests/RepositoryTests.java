package com.udacity.course3.reviews.RepositoryTests;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {
    private static Product product;
    private static Review review;
    private static Comment comment;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void addProduct(){
        product = new Product("One Plus");
        entityManager.persist(product);

        Optional<Product> db_product = productRepository.findById(product.getId());
        Assert.assertEquals(db_product.get().getProductName(),"One Plus");
    }

    @Test
    public void addReview(){
        product = new Product("One Plus");
        entityManager.persist(product);

        review = new Review();
        review.setProduct(product);
        review.setReview("Very Fast");
        entityManager.persist(review);

        Optional<Review> dbReview = reviewRepository.findById(review.getId());
        Assert.assertEquals(dbReview.get().getReview(),"Very Fast");

    }

    @Test
    public void testAll(){

        product = new Product("One Plus");
        entityManager.persist(product);

        review = new Review();
        review.setProduct(product);
        review.setReview("Very Fast");
        entityManager.persist(review);

        comment = new Comment();
        comment.setReview(review);
        comment.setComment("Never Settle");
        entityManager.persist(comment);
        Optional<Comment> dbComment = commentRepository.findById(comment.getId());
        Assert.assertEquals(dbComment.get().getComment(),"Never Settle");

        List<Review> dbReviews = reviewRepository.findAllReviewsByProduct(product);
        Assert.assertEquals(dbReviews.get(0).getReview(),"Very Fast");

        List<Comment> dbComments = commentRepository.findAllCommentsByReview(review);
        Assert.assertEquals(dbComments.get(0).getComment(), "Never Settle");
    }

}
