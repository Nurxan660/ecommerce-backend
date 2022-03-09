package com.example.ecommerce.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id",unique = true,nullable = false)
    private User user;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    List<CartItem> cartItems;



    public Cart() {

    }



    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
