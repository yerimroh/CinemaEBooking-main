package com.example.CinemaEBooking.model.room;

import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {

	Room findById(long id);
}
