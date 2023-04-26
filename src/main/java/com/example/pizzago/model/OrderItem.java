package com.example.pizzago.model;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Pizza pizza;

    private int quantity;

    public OrderItem() {}

    public OrderItem(Order order, Pizza pizza, int quantity) {
        this.order = order;
        this.pizza = pizza;
        this.quantity = quantity;
    }

    // геттеры и сеттеры для всех полей

}
