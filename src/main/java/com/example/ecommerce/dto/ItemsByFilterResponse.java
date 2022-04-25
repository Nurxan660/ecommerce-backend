package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Item;

import java.util.List;

public class ItemsByFilterResponse {

    private List<ItemsResponse> content;
    private int totalPages;

    public ItemsByFilterResponse() {
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<ItemsResponse> getContent() {
        return content;
    }

    public void setContent(List<ItemsResponse> content) {
        this.content = content;
    }
}
