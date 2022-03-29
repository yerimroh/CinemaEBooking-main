package com.example.CinemaEBooking.model.movie;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RatingCodeRepo extends CrudRepository<RatingCode, Long> {

	String getRatingById(long id);
	RatingCode findById(long id);
}
