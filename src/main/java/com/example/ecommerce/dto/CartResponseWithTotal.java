package com.example.ecommerce.dto;

import java.util.List;

public class CartResponseWithTotal {
    private Integer total;
    private List<CartItemResponse> cartItemResponseList;

    public CartResponseWithTotal() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<CartItemResponse> getCartItemResponseList() {
        return cartItemResponseList;
    }

    public void setCartItemResponseList(List<CartItemResponse> cartItemResponseList) {
        this.cartItemResponseList = cartItemResponseList;
    }
}
