package com.example.model.pojos;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {
    private int id;
    private LocalDateTime date;
    private double totalPrice;
    private Map<Product, Integer> orderedProducts;

    public Order(LocalDateTime date, double totalPrice, Map<Product, Integer> orderedProducts) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.orderedProducts = orderedProducts;
    }
}
