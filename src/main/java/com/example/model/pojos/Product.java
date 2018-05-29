package com.example.model.pojos;

import java.time.LocalDate;
import java.util.*;

public class Product implements IIdentifiable{
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Category category;
    private LocalDate date;
    private String photoUrl;
    private Set<Characteristic> characteristics;
    private Discount discount;
    private Rating rating;
    private Set<Comment> comments;
    private Set<String> photos;


    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.category = new Category();
        this.date = LocalDate.now();
        this.characteristics = new TreeSet<>((o1, o2) -> {
            if (o1.getName().equalsIgnoreCase(o2.getName())) return -1;
            return o1.getName().compareToIgnoreCase(o2.getName());
        });
        this.rating = new Rating();
        this.comments = new TreeSet<>();
        this.photos = new HashSet<>();
    }



    public Product(String name, double price, int quantity) {
        this(name, price);
        this.quantity = quantity;
    }

    public Product(int id, String name, double price, String photoUrl) {
        this(name, price);
        this.id = id;
        this.photoUrl = photoUrl;
    }

    public Product(String name, String description, double price, int quantity, String photoUrl) {
       this(name, price, quantity);
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public Product(int id, String name, String description, double price, int quantity, String photoUrl) {
        this(name, description, price, quantity, photoUrl);
        this.id = id;

    }

    public Product(int id, String name, String description, double price, int quantity, LocalDate date, String photoUrl, Category category, Discount discount) {
        this(id, name, description, price, quantity, photoUrl);
        this.date = date;
        this.discount = discount;
    }

    public Product(int id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Set<Characteristic> getCharacteristics() {
        return Collections.unmodifiableSet(characteristics);
    }

    public void addCharacteristic(Characteristic characteristic) {
        this.characteristics.add(characteristic);
    }

    public void removeCharacteristic(Characteristic characteristic) {
        this.characteristics.remove(characteristic);
    }

    public Set<Comment> getComments() {
        return Collections.unmodifiableSet(comments);
    }

    public void addReview(int rate) {
        this.rating.addRate(rate);
    }
    //TODO rating

    public double getRating() {
        return this.rating.getRating();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Product)) return false;
        Product product = (Product) object;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name) * 31;
    }

    public Set<String> getPhotos() {
        return Collections.unmodifiableSet(photos);
    }

    public void setPhotos(Set<String> photos) {
        this.photos = photos;
    }

    public void addToPhotos(String photo) {
        this.photos.add(photo);
    }


    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getCurrentPrice(){
        return discount != null ?
                        this.price - (price * (discount.getPercent() / 100)):
                        price;
    }





    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                ", date=" + date +
                ", photoUrl='" + photoUrl + '\'' +
                ", characteristics=" + characteristics +
                ", discount=" + discount.getPercent() +
                ", rating=" + rating +
                ", comments=" + comments +
                '}';
    }
}
