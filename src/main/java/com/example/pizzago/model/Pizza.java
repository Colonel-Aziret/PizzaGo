package com.example.pizzago.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "pizza")
@Getter
@Setter
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    public Pizza() {}

    public Pizza(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
