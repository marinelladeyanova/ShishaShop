package com.example.model.DAO;

import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ReviewExeption;
import com.example.model.pojos.Comment;
import com.example.model.pojos.Product;
import com.example.model.pojos.Rate;
import com.example.model.pojos.User;

import java.util.Set;

public interface IRateDAO {

    void addRating(User user, Rate rate, Product product) throws PersonException, ReviewExeption;

    boolean checkForExcistingRating(User user, Rate rate, Product product) throws PersonException, ReviewExeption;

    Set<Integer> getAllRatingsForProduct(Product product) throws PersonException;

}
