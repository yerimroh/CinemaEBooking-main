package com.example.CinemaEBooking.model.room;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SeatStatusRepo extends CrudRepository<SeatStatus, Long> {

	String getStatusById(long id);
	SeatStatus findById(long id);
}
