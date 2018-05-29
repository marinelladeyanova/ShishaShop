package com.example.model.DAO;

import com.example.model.exeptions.PersonException;
import com.example.model.pojos.PersonInformation;
import com.example.model.pojos.Product;
import com.example.model.pojos.User;

import java.util.Set;

public interface IUserDAO {


    int registerUser(PersonInformation person) throws PersonException;

    int loginUser(String username, String password) throws PersonException;

    void makeOrder(PersonInformation person) throws PersonException;

}
