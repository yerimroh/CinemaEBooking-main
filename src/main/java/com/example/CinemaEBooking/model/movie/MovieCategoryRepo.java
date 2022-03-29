package com.example.CinemaEBooking.model.movie;

import org.springframework.data.repository.CrudRepository;

public interface MovieCategoryRepo extends CrudRepository<MovieCategory, Long> {

	MovieCategory findById(long id);
}
