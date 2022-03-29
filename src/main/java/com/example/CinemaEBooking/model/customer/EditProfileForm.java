package com.example.CinemaEBooking.model.customer;

import javax.validation.constraints.*;

public class EditProfileForm {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String streetAddress;

    private String city;
    
    private String state;

    private String zipCode;

    private boolean subscribed;

/*
    public EditProfileForm(String firstName, String lastName, String phoneNumber, String getStreetAddress, String city, String state, String zipCode, boolean subscribed) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setStreetAddress(streetAddress);
        setCity(city);
        setState(state);
        setZipCode(zipCode);
        setSubscribed(subscribed);
    }
*/

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public boolean getSubscribed() {
        return subscribed;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String toString() {
        return String.format("EditProfileForm[fistName='%s', lastName='%s', "
                + "phoneNumber='%s']", firstName, lastName, phoneNumber);
    }
}
