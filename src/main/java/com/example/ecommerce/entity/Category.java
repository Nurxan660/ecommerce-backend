package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @Column(name = "category_name",nullable = false,unique = true)
    private String name;
    public Category() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
