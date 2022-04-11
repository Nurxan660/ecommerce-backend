package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Images;
import com.example.ecommerce.entity.Item;

import javax.persistence.Column;
import java.util.List;

public class ItemsByCategoryResponse {
    private List<Item> content;
    private int totalPages;

    public ItemsByCategoryResponse() {

    }

    public List<Item> getContent() {
        return content;
    }

    public void setContent(List<Item> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
