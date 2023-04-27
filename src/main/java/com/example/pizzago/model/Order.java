package com.example.pizzago.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String customerAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(String customerName, String customerAddress, List<OrderItem> orderItems) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.orderItems = orderItems;
    }
}







