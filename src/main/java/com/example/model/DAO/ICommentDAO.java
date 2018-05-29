package com.example.model.DAO;

import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ReviewExeption;
import com.example.model.pojos.Comment;
import com.example.model.pojos.Product;
import com.example.model.pojos.Rate;
import com.example.model.pojos.User;

import java.util.Set;

public interface ICommentDAO {

    void addComment(User user, Comment comment, Product product) throws PersonException, ReviewExeption;

    void removeComment(Comment comment) throws PersonException, ReviewExeption;

    Set<Integer> getAllCommentsForProduct(Product product) throws PersonException;

}
