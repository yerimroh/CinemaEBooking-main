package com.example.CinemaEBooking.model.address;

import javax.persistence.*;

import com.example.CinemaEBooking.model.customer.Customer;
import com.example.CinemaEBooking.model.payment_method.PaymentMethod;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Customer customer;

    
    @OneToOne(mappedBy="address", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private PaymentMethod paymentMethod;
    

    @Column(nullable=false)
    private String streetAddress;
    
    @Column(nullable=false)
    private String zipCode;

    @Column(nullable=false)
    private String city;

    @Column(nullable=false)
    private String state;

    public Address() {
    
    }

    public Address(String streetAddress, String zipCode, String city, String state) {
        setStreetAddress(streetAddress);
        setZipCode(zipCode);
        setCity(city);
        setState(state);
    }

    public Long getId() {
        return this.id;
    }


    @ModelAttribute("streetAddress")
    public String getStreetAddress() {
        return streetAddress;
    }

    @ModelAttribute("city")
    public String getCity() {
        return city;
    }

    @ModelAttribute("state")
    public String getState() {
        return state;
    }

    @ModelAttribute("zipCode")
    public String getZipCode() {
        return zipCode;
    }

    public void setStreetAddress(String streetAddress) {
       
        this.streetAddress = streetAddress;
    }

    public void setZipCode(String zipCode) {
        
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
       
        this.city = city;
    }

    public void setState(String state) {
       
        this.state = state;
    }


    // return if this Address is an actual address
    public boolean isAddress() {
        if (streetAddress.length() > 0 && zipCode.length() > 0 && city.length() > 0 && state.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return (streetAddress.length() == 0 && zipCode.length() == 0 && city.length() == 0 && state.length() == 0);
    }




    @Override
    public String toString() {
        return String.format(
            "Address[streetAddress=%s, zipCode=%s, city=%s, state=%s]",
            streetAddress, zipCode, city, state);
    }
}
