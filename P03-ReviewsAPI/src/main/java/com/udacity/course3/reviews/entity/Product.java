package com.udacity.course3.reviews.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String product_name;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(String product_name) {
        this.product_name = product_name;
    }

    public Product(Integer id, String product_name) {
        this.id = id;
        this.product_name = product_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                '}';
    }
}
