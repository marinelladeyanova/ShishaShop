package com.example.model.DAO;

import com.example.model.exeptions.PersonException;
import com.example.model.pojos.PersonInformation;
import com.example.model.pojos.Product;
import com.example.model.pojos.User;

import java.util.Set;

public interface IFavouriteProductsDAO {

    void addFavouriteProduct(User user, Product product) throws PersonException;

    void removeFavouriteProduct(User user, Product product) throws PersonException;

    Set<Integer> getAllFavouriteProducts(User user) throws PersonException;

    Set<Integer> getAllFavoredUsers(User product) throws PersonException;


}
