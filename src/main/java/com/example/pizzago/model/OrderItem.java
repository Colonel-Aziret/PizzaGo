package com.example.pizzago.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
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


}
