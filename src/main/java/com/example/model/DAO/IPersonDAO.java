package com.example.model.DAO;

import com.example.model.exeptions.PersonException;
import com.example.model.pojos.PersonInformation;


public interface IPersonDAO {

    int register(PersonInformation person, String statemnet) throws PersonException;

    int login(String username, String password, String statemnet) throws PersonException;

    void editProfile(PersonInformation person, int id) throws PersonException;

    void removeProfile(PersonInformation person) throws PersonException;

}
