package com.example.CinemaEBooking.model.room;

import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Long> {

	Seat findById(long id);
}
