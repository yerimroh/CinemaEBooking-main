package com.example.CinemaEBooking.model.room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seatstatus")
public class SeatStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id; // 1: Available, 2: Unavailable, 3: Selected
    private String status;



	protected SeatStatus() {}

	public SeatStatus(String status) {
        setStatus(status);
    } // SeatStatus


	
    /* getters */
	public String getStatus() {
		return this.status;
	}


    /* setters */
    public void setStatus(String status) {
        this.status = status;
    }


}
