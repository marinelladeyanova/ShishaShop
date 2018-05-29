package com.example.model.DAO;

import com.example.model.exeptions.PersonException;
import com.example.model.pojos.PersonInformation;

public interface IAdminDAO {

    int registerAdmin(PersonInformation person) throws PersonException;
    int loginAdmin(String username, String password) throws PersonException;


}
