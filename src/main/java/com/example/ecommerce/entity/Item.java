package com.example.ecommerce.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itmId;
    @Column(name = "item_name",nullable = false)
    private String name;
    @Column(name="item_description",nullable = false,columnDefinition = "text")
    private String description;
    @Column(name="item_price",nullable = false)
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "item")
    private List<Images> images;
    public Item() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getItmId() {
        return itmId;
    }

    public void setItmId(Long itmId) {
        this.itmId = itmId;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }
}
