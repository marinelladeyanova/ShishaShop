package com.example.model.pojos;

import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ValidationExeption;

import java.time.LocalDate;

public abstract class PersonInformation {
    private int personId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private LocalDate birthday;
    private String email;
    private String confirmPassword;

    public PersonInformation() {
    }

    public PersonInformation(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = new Address();
    }

    /*public PersonInformation(String username, String password, String email)  {
            setUsername(username);
            setPassword(password);
            setEmail(email);
            this.address = new Address();
        }
    */
    public PersonInformation(String username, String email, String password, String confirmPassword) {
       /* this(username, password, email);
        setConfirmPassword(confirmPassword);*/
       this.username = username;
       this.email = email;
       this.password = password;
       this.confirmPassword = confirmPassword;
    }


    public void validate () throws PersonException {
        try {
            this.username = Validator.getInstance().validateString(username);
        } catch (ValidationExeption e) {
            throw new PersonException("Incorrect username. " + e.getMessage(), e);
        }
        try {
            this.password = Validator.getInstance().validatePassword(password);
        } catch (ValidationExeption e) {
            throw new PersonException("Incorrect password. " + e.getMessage(), e);
        }
        try {
            this.email = Validator.getInstance().validateEmail(email);
        } catch (ValidationExeption e) {
            throw new PersonException(e.getMessage(), e);
        }
        if (!(this.confirmPassword.equals(this.password)))
            throw new PersonException("The confirming password must mach.");
    }





    public PersonInformation(int personId, String username, String password, String firstName, String lastName, Address address, String phoneNumber, LocalDate birthday, String email) {
        this(username, password, email);
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.setPersonId(personId);

    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
            this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws PersonException {
        try {
            this.lastName = Validator.getInstance().validateName(lastName);
        } catch (ValidationExeption e) {
            throw new PersonException("Incorrect last name. " + e.getMessage(), e);
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws PersonException {
        try {
            this.phoneNumber = Validator.getInstance().validatePhonenumber(phoneNumber);
        } catch (ValidationExeption e) {
            throw new PersonException("Incorrect phone number. " + e.getMessage(), e);
        }
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws PersonException {
        this.email = email;
    }



}
