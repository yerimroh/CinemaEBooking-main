package com.example.CinemaEBooking.model.movie;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	Movie findById(long id);
}
