package com.switchfully.eurder.domain.models;

public class Address {
    private final String street;
    private final String streetNumber;
    private final String city;
    private final String postalCode;

    public Address(String street, String streetNumber, String city, String postalCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
