package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Images;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ItemsResponse {
    private Long itmId;
    @JsonProperty("name")
    private String itemName;
    @JsonProperty("price")
    private Integer itemPrice;
    private List<Images> images;


    public ItemsResponse() {

    }

    public Long getItmId() {
        return itmId;
    }

    public void setItmId(Long itmId) {
        this.itmId = itmId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }
}
