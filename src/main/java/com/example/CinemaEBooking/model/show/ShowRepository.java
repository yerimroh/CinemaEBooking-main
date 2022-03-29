package com.example.CinemaEBooking.model.show;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long> {

	Show findById(long id);
}
