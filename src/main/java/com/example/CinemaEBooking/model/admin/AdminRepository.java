package com.example.CinemaEBooking.model.admin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
	List<Admin> findByLastName(String lastName);

	Admin findById(long id);

	Admin findByEmail(String email);
}
