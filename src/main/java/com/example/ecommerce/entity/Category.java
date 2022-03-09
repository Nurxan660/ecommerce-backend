package com.example.ecommerce.entity;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "category_name",nullable = false)
    private String name;
    public Category() {

    }
}
