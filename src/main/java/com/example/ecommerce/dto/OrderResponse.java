package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Order;

public class OrderResponse {
    private String message;
    private Order order;

    public OrderResponse(String message, Order order) {
        this.message = message;
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
