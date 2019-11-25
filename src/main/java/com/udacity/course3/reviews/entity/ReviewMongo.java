package com.udacity.course3.reviews.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "reviews")
public class ReviewMongo {
    @Id
    private Integer id;
    private String review;
    private Integer likes;

    public ReviewMongo(Integer id, String review, Integer likes) {
        this.id = id;
        this.review = review;
        this.likes = likes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "ReviewMongo{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", likes=" + likes +
                '}';
    }
}
