package com.example.model.pojos;

import java.util.*;

public class Category {
    private int id;
    private String name;
    private Set<Category> subcategories;
    private Set<Product> products;

    public Category() {
        this.subcategories = new TreeSet<>(Comparator.comparingInt(Category::getId));
        this.products = new TreeSet<>(Comparator.comparing(Product::getDate));
    }


    public Category(String name) {
        this();
        this.name = name;
    }

    public Category(int id, String name) {
        this(name);
        this.id = id;
    }

    public Category(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Category> getSubcategories() {
        return Collections.unmodifiableSet(subcategories);
    }

    public void addSubcategory(Category subcategory) {
        this.subcategories.add(subcategory);
    }

    public void setSubcategories(Set<Category> subcategories) {
        this.subcategories.addAll(subcategories);
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void setProducts(Set<Product> products) {
        this.products.addAll(products);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

}
