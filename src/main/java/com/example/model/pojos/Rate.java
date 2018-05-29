package com.example.model.pojos;

import com.example.model.exeptions.ReviewExeption;

import java.time.LocalDateTime;
import java.util.Objects;

public class Rate {
    public static final int MIN_RATING_VALUE = 1;
    public static final int MAX_RATING_VALUE = 5;

    private int id;
    private int rating;
    private LocalDateTime date;



    public Rate() {
    }



    public Rate(int id) throws ReviewExeption {
        this.date = LocalDateTime.now();
        this.id = id;
    }

    public Rate(int rating, String s) throws ReviewExeption {
        this.date = LocalDateTime.now();
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) throws ReviewExeption {
        if (rating < MIN_RATING_VALUE || rating > MAX_RATING_VALUE)
            throw new ReviewExeption("Invalid rating, must be between " + MIN_RATING_VALUE + " and " + MAX_RATING_VALUE);
        this.rating = rating;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Rate)) return false;
        Rate rate = (Rate) object;
        return Objects.equals(date, rate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date.toString()) * 31;
    }

}
