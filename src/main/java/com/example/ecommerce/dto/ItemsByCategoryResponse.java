package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Images;

import javax.persistence.Column;
import java.util.List;

public class ItemsByCategoryResponse {
    private Long itmId;
    private String name;
    private String description;
    private Integer price;
    private List<Images> imagesList;

    public ItemsByCategoryResponse() {

    }

    public Long getItmId() {
        return itmId;
    }

    public void setItmId(Long itmId) {
        this.itmId = itmId;
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

    public List<Images> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<Images> imagesList) {
        this.imagesList = imagesList;
    }
}
