package com.example.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer qty;
    @ManyToOne()
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


    public Cart getCart() {
        return cart;
    }

    public CartItem(Integer qty, Cart cart, Item item) {
        this.qty = qty;
        this.cart = cart;
        this.item = item;
    }

    public CartItem(Integer qty) {
        this.qty = qty;
    }
    public CartItem() {

    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
