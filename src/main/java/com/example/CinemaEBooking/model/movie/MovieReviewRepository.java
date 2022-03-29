package com.example.CinemaEBooking.model.movie;

import org.springframework.data.repository.CrudRepository;

public interface MovieReviewRepository extends CrudRepository<MovieReview, Long> {

	MovieReview findById(long id);
}
