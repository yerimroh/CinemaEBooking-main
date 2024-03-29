package com.example.CinemaEBooking.model.booking;

import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {

	Booking findById(long id);
}
