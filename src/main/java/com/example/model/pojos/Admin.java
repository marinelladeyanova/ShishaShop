package com.example.model.pojos;

import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ValidationExeption;

public class Admin extends PersonInformation{
    private int adminId;

    public Admin(String username, String password, String email) throws ValidationExeption, PersonException {
        super(username, password, email);
    }

    public int getAdminIdId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
