package com.example.CinemaEBooking.model.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ratingcodes")
public class RatingCode {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id; // 1: G, 2: PG, 3: PG-13, 4: R, 5: NC-17
    private String ratingCode;



	protected RatingCode() {}

	public RatingCode(String ratingCode) {
        setRatingCode(ratingCode);
    } // RatingCode


	
    /* getters */
	public String getRatingCode() {
		return this.ratingCode;
	}


    /* setters */
    public void setRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
    }


}
