package com.example.CinemaEBooking.model.customer;

import java.util.List;
import javax.persistence.*;

import com.example.CinemaEBooking.model.address.Address;
import com.example.CinemaEBooking.model.payment_method.PaymentMethod;
import com.example.CinemaEBooking.model.user.Role;
import com.example.CinemaEBooking.model.user.User;

import org.springframework.web.bind.annotation.*;

@Entity
@Table(name = "customer")
public class Customer extends User {

	@Column(nullable = false, unique = true)
	public String phoneNumber;	

    @Enumerated(EnumType.STRING)
    public CustomerStatus status;

    // composition 
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="address_id")
    private Address address;

    // represents whether this user is subscribed to promotions or not
    @Column(name = "subscribed") 
    private boolean subscribed;

    // TO DO: Enforce that this is capped at 3.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PaymentMethod> paymentMethods;

    @Transient
    private PaymentMethod paymentMethod;



    /*
    * tokens used to verify or reset password
    */
    // will be used to verify user and change its state to active    
    @Column(name = "verificationCode", length = 64) 
    private String verificationCode;
    


    @Column(name = "resetPasswordToken", length = 20)
    private String resetPasswordToken;




    // constructor
	public Customer() {
        status = CustomerStatus.INACTIVE;
        role = Role.ROLE_MEMBER; // customer is always a customer
    }

	public Customer(String firstName, String lastName, String email,
		String encryptedPassword, String phoneNumber, boolean subscribed) {
        super(firstName, lastName, email, encryptedPassword);
        status = CustomerStatus.INACTIVE;
        role = Role.ROLE_MEMBER; // customer is always a customer
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		setEncryptedPassword(encryptedPassword);
        setSubscribed(subscribed);
	}	


    // getters
    @ModelAttribute("phoneNumber")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

    @ModelAttribute("address")
    public Address getAddress() {
        return this.address;
    }

    @ModelAttribute("paymentMethod")
    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    
    public CustomerStatus getStatus() {
        return this.status;
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }


    public String getResetPasswordToken() {
        return this.resetPasswordToken;
    }


    public boolean getSubscribed() {
        return this.subscribed;
    }


    // setters
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
 
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
	}

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public void setSubscribed(boolean condition) {
        this.subscribed = condition;
    }


    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }




    // other methods
    public void addPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethods.size() < 3) {
            paymentMethods.add(paymentMethod);
        }
    }


    // return if this account is yet to verify
    public boolean isVerified() {
        boolean isActive = true;
        if(this.status == CustomerStatus.INACTIVE) {
            isActive = false;
        } 
        return isActive;
    } // isActive




	@Override
	public String toString() {
		return String.format(
		"Customer[id=%d, firstName='%s', lastName='%s', email='%s', " + 
            "status='%s', address='%s']",
            id, firstName, lastName, email, status, address);
	}
}
