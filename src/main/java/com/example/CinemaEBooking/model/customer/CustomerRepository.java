package com.example.CinemaEBooking.model.customer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);

	Customer findById(long id);

	Customer findByEmail(String email);

    Customer findByVerificationCode(String verificationCode);

	Customer findByResetPasswordToken(String resetPasswordToken);


}
