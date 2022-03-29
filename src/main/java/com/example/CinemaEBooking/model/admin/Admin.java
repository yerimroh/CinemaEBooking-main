package com.example.CinemaEBooking.model.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.CinemaEBooking.model.user.Role;
import com.example.CinemaEBooking.model.user.User;

@Entity
@Table(name = "admin")
public class Admin extends User {

	protected Admin() { }
	public Admin(String firstName, String lastName, String email,
		String encryptedPassword) {
        super(firstName, lastName, email, encryptedPassword);
		role = Role.ROLE_ADMIN; // set to admin
	}	

	@Override
	public String toString() {
		return String.format(
		"Admin[id=%d, firstName='%s', lastName='%s', email='%s'",
		firstName, lastName, email);
	}
}
