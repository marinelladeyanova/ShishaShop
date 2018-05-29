package com.example.model.pojos;

import java.util.LinkedList;
import java.util.List;

public class Rating {

    private int rating;
    private List<Integer> ratings;

    Rating() {
        this.rating = 0;
        this.ratings = new LinkedList<>();
    }

    void addRate(int rating) {
        this.ratings.add(rating);
    }

    double getRating() {
        return  this.ratings.stream().mapToInt(Integer::intValue).sum() / this.ratings.size();
    }



}
