package com.example.model.pojos;

import com.example.model.exeptions.AddressException;
import com.example.model.exeptions.ValidationExeption;

public class Address {
    private int id;
    private String city;
    private String street;
    private int stretNumber;
    private String country;

    public Address() {
    }

    public Address(String city) throws AddressException {
        setCity(city);
    }

    public Address(String city, String street, int stretNumber) throws AddressException {
        this(city);
        this.setStreet(street);
        this.setStreetNumber(stretNumber);
    }

    public Address(int id, String city, String street, int stretNumber, String country) throws AddressException {
        this(city, street, stretNumber);
        this.setId(id);
        this.country = country;
    }

    public void setStretNumber(int stretNumber) {
        this.stretNumber = stretNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws AddressException {
        try {
            this.id = Validator.getInstance().validateInt(id);
        } catch (ValidationExeption e) {
            throw new AddressException("Incorrect id", e);
        }
    }

    public String getCity() throws AddressException {
        try {
            return Validator.getInstance().exsistingString(this.city);
        } catch (ValidationExeption e) {
            throw new AddressException("Unknown value", e);
        }
    }

    public void setCity(String city) throws AddressException {
        try {
            this.city = Validator.getInstance().validateString(city);
        } catch (ValidationExeption e) {
            throw new AddressException("Incorrect city", e);
        }
    }

    public String getStreet() throws AddressException {
        try {
            return Validator.getInstance().exsistingString(this.street);
        } catch (ValidationExeption e) {
            throw new AddressException("Unknown value", e);
        }
    }

    public void setStreet(String street) throws AddressException {
        try {
            this.street = Validator.getInstance().validateString(street);
        } catch (ValidationExeption e) {
            throw new AddressException("Incorrect street", e);
        }
    }

    // TODO exception here
    public int getStretNumber() {
        return stretNumber;
    }


    public void setStreetNumber(int stretNumber) {
        this.stretNumber = stretNumber;
    }
}
