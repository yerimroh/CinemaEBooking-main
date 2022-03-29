package com.example.CinemaEBooking.model.booking;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*
    One booking has many tickets
    One booking is associated with One show
    Many bookings are made by one customer
*/

@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long bookingId; // primary key

    private String bookingNumber;
    private double orderTotal;
    private LocalDateTime dateOfBooking;

    private long showId; // foreign key to access show info
    private long customerId; // foreign key to access customer info

	protected Booking() {}

	public Booking(String bookingNumber, double orderTotal, LocalDateTime dateOfBooking, long showId) {
        setBookingNumber(bookingNumber);
        setOrderTotal(orderTotal);
        setDateOfBooking(dateOfBooking);
        setShowId(showId);
    } // Booking


	
    /* getters */
    public long getCustomerId() {
        return this.customerId;
    }

    public String getBookingNumber() {
        return this.bookingNumber;
    }

    public double getOrderTotal() {
        return this.orderTotal;
    }

    public LocalDateTime getDateOfBooking() {
        return this.dateOfBooking;
    }

    public long getShowId() {
        return this.showId;
    }



    /* setters */
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void setDateOfBooking(LocalDateTime dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public void setShowId(long showId) {
        this.showId = showId;
    }



    /* method */
    

}
