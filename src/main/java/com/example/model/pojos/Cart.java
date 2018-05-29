package com.example.model.pojos;

import com.example.model.exeptions.CartException;

import java.util.*;

public class Cart {

    private int cart_id;
    private int user_id;
    private Set<Product> products;
    private double totalPrice;

    public Cart(int user_id) {
        this.user_id = user_id;
        this.products = new HashSet<>();
        this.totalPrice = 0;
    }

    public int getCart_id() throws CartException {
        if (this.cart_id == 0)
            throw new CartException("Unknown cart id");
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUser_id() throws CartException {
        if (user_id == 0)
            throw new CartException("Unknown user id");
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Set<Product> getProducts() throws CartException {
        return Collections.unmodifiableSet(products);
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProducts(Product product) throws CartException {
        if (this.products.contains(product)) {
            this.products.remove(product);
        }
        throw new CartException("No such product");
    }
/*
    public void incrementQuantity(Product product) throws CartException {
        if (this.products.containsKey(product)) {
           this.products.put(product, this.products.get(product) + 1);
        }
        throw new CartException("No such product");
    }

    public void decrementQuantity(Product product) throws CartException {
        if (!this.products.containsKey(product))
            throw new CartException("No such product");
        if (this.products.get(product) == 0)
            throw new CartException("No quantity");
        this.products.put(product, this.products.get(product) - 1);
    }*/

    public double getTotalPrice() {
        return this.products
                .stream()
                .mapToDouble(Product::getCurrentPrice)
                .sum();
    }
}
