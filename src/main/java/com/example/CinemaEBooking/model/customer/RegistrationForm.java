package com.example.CinemaEBooking.model.customer;

import javax.validation.constraints.*;

/*
 * Yes this is garbage. Yes we need to decompose into AddressForm and 
 * PaymentMethodForm with AddressValidator and PaymentMethodValidator. No time
 * right now.
 */

public class RegistrationForm {

    /* Customer info */

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;
    
    private String password;

    /* Address info */

    private String streetAddress;

    private String city;
    
    private String state;

    private String zipCode;

    /* Payment method info */

    private String cardNumber;

    private String expirationDateString;

    private String ccv;

    private String billingStreetAddress;

    private String billingCity;

    private String billingState;

    private String billingZipCode;

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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public String getExpirationDateString() {
        return expirationDateString;
    }

    public String getCcv() {
        return ccv;
    }

    public String getBillingStreetAddress() {
        return billingStreetAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public String getBillingZipCode() {
        return billingZipCode;
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
   
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setExpirationDateString(String expirationDateString) {
        this.expirationDateString = expirationDateString;
    }
    public void setCcv(String ccv) {
        this.ccv = ccv;
    }
    public void setBillingStreetAddress(String billingStreetAddress) {
        this.billingStreetAddress = billingStreetAddress;
    }
    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }
}
