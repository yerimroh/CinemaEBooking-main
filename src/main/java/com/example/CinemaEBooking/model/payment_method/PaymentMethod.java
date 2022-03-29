package com.example.CinemaEBooking.model.payment_method;

import javax.persistence.*;

import com.example.CinemaEBooking.model.address.Address;
import com.example.CinemaEBooking.model.customer.Customer;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.annotation.Transient;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="payment_method")
public
class PaymentMethod {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    // 계정 > 카드 > 빌링주소 이런느낌인가 
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // composition
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(nullable = false, unique = true)
    private String encryptedNumber;

    @Transient
    private String expirationDateString;
    
    @Transient
    private String unencryptedNumber;

    @Transient
    private String ccv;

    @Transient
    private String endingDigit;

    public PaymentMethod() { }


    // constructor
    public PaymentMethod(String unencryptedNumber, String expirationDateString, String ccv) {
        setUnencryptedNumber(unencryptedNumber);
        setExpirationDateString(expirationDateString);
        setCcv(ccv);
        setEndingDigit();
        setEncryptedNumber(unencryptedNumber);
    }

    public Long getId() {
        return id;
    }

    public String getEncryptedNumber() {

        return encryptedNumber;
    }

    public Customer getCustomer() {

        return customer;
    }

    public Address getAddress() {

        return address;
    }

    @ModelAttribute("unencryptedNumber")
    public String getUnencryptedNumber() {
        return unencryptedNumber;
    }

    @ModelAttribute("ccv")
    public String getCcv() {
        return ccv;
    }

    public String getEndingDigit() {
        return this.endingDigit;
    }

    @ModelAttribute("expriationDateString")
    public String getExpirationDateString() {
        return expirationDateString;
    }


    public void setEncryptedNumber(String unencryptedNumber) {
        BCryptPasswordEncoder creditCardEncoder = new BCryptPasswordEncoder();
        this.encryptedNumber = creditCardEncoder.encode(unencryptedNumber);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUnencryptedNumber(String unencryptedNumber) {
        this.unencryptedNumber = unencryptedNumber;
    }

    public void setExpirationDateString(String expirationDate) {
        this.expirationDateString = expirationDate;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public void setEndingDigit() {
        this.endingDigit = unencryptedNumber.substring(unencryptedNumber.length() - 4);
    }


    public boolean isPayment() {
        if (expirationDateString.length() > 0 && unencryptedNumber.length() > 0 && ccv.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return ((expirationDateString.length() == 0 && unencryptedNumber.length() == 0 && ccv.length() == 0));
    }


    @Override
    public String toString() {
        return String.format(
            "PaymentMethod[encryptedNumber=%s, customer=%s, address=%s]", 
            encryptedNumber, customer, address);
    }
}
