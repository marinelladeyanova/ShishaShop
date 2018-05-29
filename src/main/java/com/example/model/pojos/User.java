package com.example.model.pojos;

import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ValidationExeption;

import java.time.LocalDate;
import java.util.*;

public class User extends PersonInformation implements IIdentifiable{
    private int userId;
    private Set<Product> favouriteProducts;
    private Set<Order> orders;
    private Map<Product, Set<Rate>> reviews;
    private Cart cart;

    public User(){
    }
    public User(int id){
        this.userId =  id;
    }

   /* public User(String username, String password) throws PersonException {
        setUsername(username);
        setPassword(password);
        this.favouriteProducts = new HashSet<>();
        this.orders = new HashSet<>();
        this.cart = new Cart(this.userId);
        this.reviews = new HashMap<>();
    }*/

    public User(String username, String password, String email) {
        super(username, password, email);
        this.favouriteProducts = new HashSet<>();
        this.orders = new HashSet<>();
        this.cart = new Cart(this.userId);
        this.reviews = new HashMap<>();
    }

    public User(String username, String email, String password, String confirmPassword) {
        super(username, email, password, confirmPassword);
        this.favouriteProducts = new HashSet<>();
        this.orders = new HashSet<>();
        this.cart = new Cart(this.userId);
        this.reviews = new HashMap<>();
    }

    public User(int personId, String username, String password, String firstName, String lastName, Address address, String phoneNumber, LocalDate birthday, String email){
        super(personId, username, password, firstName, lastName, address, phoneNumber, birthday, email);
        this.favouriteProducts = new HashSet<>();
        this.orders = new HashSet<>();
        this.cart = new Cart(this.userId);
        this.reviews = new HashMap<>();
    }


    public void seUserId(int userId) {
        this.userId = userId;
    }

    public Set<Product> getFavouriteProducts() {
        return Collections.unmodifiableSet(favouriteProducts);
    }

    public void addFavouriteProduct(Product product) {
        this.favouriteProducts.add(product);
    }

    public void removeFavouriteProduct(Product product) {
        this.favouriteProducts.remove(product);
    }

   /* public Map<Product, Integer> getOrderedProducts() {
        return Collections.unmodifiableMap(orderedProducts);
    }

    public void addOrderedProducts(Map<Product, Integer> orderedProducts) {
        for (Map.Entry<Product, Integer> productEntry : orderedProducts.entrySet()) {
            if (this.orderedProducts.containsKey(productEntry.getKey())){
                this.orderedProducts.put(productEntry.getKey()
                        , this.orderedProducts.get(productEntry.getKey()) + productEntry.getValue());
            } else {
                this.orderedProducts.put(productEntry.getKey(), productEntry.getValue());
            }
        }
    }*/


    public Set<Order> getOrders() {
        return Collections.unmodifiableSet(orders);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public Cart getCart() {
        return cart;
    }

    public Map<Product, Set<Rate>> getReviews() {
        return Collections.unmodifiableMap(reviews);
    }

    public void addReview(Product product, Rate rate) {
        if (!this.reviews.containsKey(product))
            this.reviews.put(product, new HashSet<>());
        this.reviews.get(product).add(rate);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFavouriteProducts(Set<Product> favouriteProducts) {
        this.favouriteProducts = favouriteProducts;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void setReviews(Map<Product, Set<Rate>> reviews) {
        this.reviews = reviews;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int getId() {
        return userId;
    }
}
